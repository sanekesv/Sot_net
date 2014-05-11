<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.04.14
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="css/login.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="block">
    <form method="post" action="/login" class="slick-login">
        ${error}
        <input type="text" name="username" class="placeholder"
               placeholder="admin@example.com">
        <input type="password" name="password" class="placeholder"
               placeholder="Сложный пароль...">
        <input type="submit" value="Войти">  <br>
        <a href="registration" >Зарегистрироваться</a>
    </form>
</div>
</body>
</html>
