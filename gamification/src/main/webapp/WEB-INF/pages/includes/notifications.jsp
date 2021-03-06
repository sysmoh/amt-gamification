<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty _messages}">
    <div style="position:absolute;width:300px;top:1em;right:1em;z-index:10">
        <c:forEach items="${_messages}" var="message">
            <div class="alert alert-${message.type} alert-dismissible fade show" role="alert">
                <span class="alert-inner--icon"><i class="ni ni-like-2"></i></span>
                <span class="alert-inner--text">${message.message}</span>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:forEach>
    </div>
</c:if>
