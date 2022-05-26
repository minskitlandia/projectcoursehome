<%@ page import="javax.naming.Context" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/list">Users</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/new">Добавить пользователя</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ads">Объявления</a>
        </li>


        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
    </ul>
</nav>
