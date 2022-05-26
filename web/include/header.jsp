<%@ page import="static web.Util.*" %>
<%@ page import="web.Util" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="logo">
        <img src="<%=URL%>/img/logo.png" alt="картинка">
    </div>

    <c:if test="<%=Util.user.getId()!= 0%>">
        <h3>Приветствую тебя <%=Util.user.getLogin()%>
        </h3>
    </c:if>

    <c:if test="<%=Util.user.getId()== 0%>">
        <h3>Приветствую тебя гость</h3>
    </c:if>

    <div class="auth">
        <a class="btn btn-outline-success" href="<%=request.getContextPath()%>/signin">SignIn</a>
        <a class="btn btn-outline-danger" href="<%=request.getContextPath()%>/signup">SignUp</a>
    </div>
</div>
