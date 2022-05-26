<%--
  Created by IntelliJ IDEA.
  User: naymc
  Date: 21.05.2022
  Time: 3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%@include file="../include/head.jsp" %>
<body>


<%@include file="../include/header.jsp" %>
<%@include file="../include/nav.jsp" %>


<div class="container">
    <c:if test="${ads != null}">
    <form action="<%=request.getContextPath()%>/ads-update" method="post">
        <caption>
            <h2>
                Edit Ads
            </h2>
        </caption>
    </c:if>
    <c:if test="${ads == null}">
    <form action="<%=request.getContextPath()%>/ads-add" method="post">
        <caption>
            <h2>
                Add Ads
            </h2>
        </caption>
        </c:if>


            <c:if test="${ads != null}">
                <fieldset class="form-group">
                    <label>Ads ID</label>
                    <input type="text" readonly name="id" value="<c:out value='${ads.id}' />"/>
                </fieldset>
            </c:if>


            <fieldset class="form-group">
                <label>Заголовок объявления</label>
                <input type="text" value="<c:out value='${ads.title}' />"
                       class="form-control" name="title" required="required"/>
            </fieldset>

            <fieldset class="form-group">
                <label>Подзаголовок объявления</label>
                <input type="text" value="<c:out value='${ads.subtitle}' />"
                       class="form-control" name="subtitle" required="required"/>
            </fieldset>

            <fieldset class="form-group">
                <label>Основной текст объявлениея</label>
                <textarea class="form-control" name="description" required="required">
                    <c:out value='${ads.description}' />
                </textarea>
            </fieldset>

            <fieldset class="form-group">
                <label>Цена</label>
                <input type="text" value="<c:out value='${ads.price}' />"
                       class="form-control" name="price" required="required"/>
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>

            <c:if test="${ads!=null}">
                <a href="<%=request.getContextPath()%>/ads-delete?id=<c:out value='${ads.id}' />" class="btn btn-danger">
                    удалить данное объявление</a>
            </c:if>

        </form>
</div>


</body>
</html>
