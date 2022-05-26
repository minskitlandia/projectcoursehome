<%--
  Created by IntelliJ IDEA.
  User: naymc
  Date: 19.05.2022
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%@include file="../include/head.jsp" %>
<body>


<%@include file="../include/header.jsp" %>
<%@include file="../include/nav.jsp" %>


<c:if test="<%=Util.USER.getId()!= 0%>">
    <div class="container text-left">
        <a href="<%=request.getContextPath()%>/ads-new" class="btn btn-success">Создать объявление</a>
    </div>
</c:if>


<c:forEach var="ads" items="${ads}">
    <div class="container">
        <div class="search">

        </div>
        <div class="content">
            <div class="ads">
                <img src="<%=URL%>/img/${ads.image}">
            </div>
            <div class="content-body">

                <h2 style="color: black;"><c:out value="${ads.title}"/></h2>
                <h5 style="color: green;"><c:out value="${ads.subtitle}"/></h5>
                <span>
                        <p><c:out value="${ads.description}"/> <a href="#">Подробнее</a></p>
                    </span>
                <div class="price" style="color: orange; font-size: 25px; "><b><c:out value="${ads.price}"/></b></div>
                <a class="edit-ads btn btn-outline-dark" href="ads-edit?id=<c:out value='${ads.id}'/>">Редактировать</a>
            </div>
        </div>
    </div>
</c:forEach>


<a href="<%=request.getContextPath()%>/ads#" class="btn btn-outline-warning pageup">на верх</a>


<footer>
    footer
</footer>

</body>
</html>
