package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.session.IFlashBagLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "makeBanServlet", urlPatterns = {"/admin/ban"})
public class MakeBanServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
     IFlashBagLocal flashbag;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // ban or unban
        String action   = ServletUtil.getString(request.getParameter("action"), "ban").toLowerCase();
        Long accountId  = ServletUtil.getLong(request.getParameter("accountId"), null);

        if(!action.equals("ban") && !action.equals("unban"))
            throw new ServletException();

        if(accountId == null) {
            flashbag.warning("Account not found");
        }

        else {
            try {

                Account account = accountDAO.find(accountId);
                account.setBanned(action.equals("ban"));
                accountDAO.update(account);
                flashbag.info("Account " + account.getEmail() + " got " + action + "ned");

            } catch (EntityNotFoundException e) {
                throw new ServletException();
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/accounts");
    }
}