<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 04.05.14
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/registration.css" type="text/css" rel="stylesheet">
    <title>Registration</title>
</head>
<body>
<div class="container">
<form action="/registration" method="post">
    ${errorregistr}<br>
    <h1>Форма регистрации</h1>
    <dl>
        <dt>Login:</dt>
            <dd>
                <input type="text" name="login" />
            </dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd>
                <input type="password" name="password" />
            </dd>
        </dl>
        <dl>
            <dt>Repeat Password:</dt>
            <dd>
                <input type="password" name="repassword" />
            </dd>
        </dl>
    <input type="submit" class="btn btn-orange" value="REGISTRED">
    <a href="/login" class="btn btn-orange" > HOME </a>
</form>
</div>
</body>
</html>
