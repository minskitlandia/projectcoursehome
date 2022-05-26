<%--
  Created by IntelliJ IDEA.
  User: naymc
  Date: 26.05.2022
  Time: 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>авториязация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
</head>
<body>
<div class="container">
    <div style="margin: 150px 30%">

        <form action="<%=request.getContextPath()%>/signin" method="post">

            <fieldset class="form-group mb-5">
                <input type="text" placeholder="login" class="form-control" name="login" required="required"/>
            </fieldset>

            <fieldset class="form-group mb-5">
                <input type="password" class="form-control" name="password" required="required"/>
            </fieldset>

            <fieldset class="form-group mb-5">
                <button type="submit" class="btn btn-primary">LOGIN</button>
            </fieldset>

        </form>
    </div>
</div>


</body>
</html>
