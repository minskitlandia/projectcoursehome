<%--
  Created by IntelliJ IDEA.
  User: naymc
  Date: 28.04.2022
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<html>

<%@include file="../include/head.jsp" %>

<body>

<%@include file="../include/header.jsp" %>
<%@include file="../include/nav.jsp" %>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${user == null}">
                <form action="insert" method="post">
            </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <fieldset class="form-group">
                            <label>User ID</label>
                            <input type="text" readonly name="id" value="<c:out value='${user.id}' />"/>
                        </fieldset>
                    </c:if>

                    <fieldset class="form-group">
                        <label>User login</label>
                        <input type="text" value="<c:out value='${user.login}' />"
                               class="form-control" name="login" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label>
                        <input type="text" value="<c:out value='${user.password}' />"
                               class="form-control" name="password">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Role_id</label>
                        <input type="text" value="<c:out value='${user.role_id}' />"
                               class="form-control" name="role_id">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>