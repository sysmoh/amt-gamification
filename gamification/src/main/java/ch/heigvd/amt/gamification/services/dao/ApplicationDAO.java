package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.ApiCall;
import ch.heigvd.amt.gamification.Model.entity.Application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ApplicationDAO extends GenericDAO<Application, Long> implements IApplicationDAOLocal {

    @EJB
    IApiCallDaoLocal apiCallDAO;

    @Override
    public List<Application> findDeveloperApps(Long id, Integer page, Integer amount) {
        return em.createNamedQuery("Application.findByDeveloper").setParameter("id", id).setFirstResult(page*amount)
                .setMaxResults(amount).getResultList();
    }

    @Override
    public Long countDeveloperApps(Long id) {
        return (Long)em.createNamedQuery("Application.countForDeveloper").setParameter("id", id).getSingleResult();
    }

    @Override
    public void delete(Application application) throws EntityNotFoundException {

        for(Object item : em.createNamedQuery("ApiCall.findByApplication").setParameter("id", application.getId()).getResultList())
            apiCallDAO.delete((ApiCall)item);

        super.delete(application);
    }
}
