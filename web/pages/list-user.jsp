<%--
  Created by IntelliJ IDEA.
  User: naymc
  Date: 28.04.2022
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<%@include file="../include/head.jsp" %>

<body>

<%@include file="../include/header.jsp" %>
<%@include file="../include/nav.jsp" %>


<div class="row">
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>

        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
        </div>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role_id</th>
                <th>ACTIONS</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">

                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.password}"/>
                    </td>
                    <td>
                        <c:out value="${user.role_id}"/>
                    </td>
                    <td>
                        <a href="edit?id=<c:out value ='${user.id}' />">Edit</a>
                        <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>

</html>
