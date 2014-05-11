<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 07.05.14
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="css/registration.css" type="text/css" rel="stylesheet">
</head>
<body>
<header>
    <div class="nav_all">
        <a href="/alluser">All Users</a>
        <a href="/logout">Logout</a>
    </div>
</header>
<div class="container">
<form action="/setinfo" method="post">

    <h1>Форма редактирования</h1>
    <dl>
        <dt>First Name:</dt>
        <dd>
            <input type="text" name="firstname"/>
        </dd>
    </dl>
    <dl>
        <dt>Last name:</dt>
        <dd>
            <input type="text" name="lastname"/>
        </dd>
    </dl>
    <dl>
        <dt>Birth date:</dt>
        <dd>
            <input type="text" name="bdate"/>
        </dd>
    </dl>
    <dl>
        <dt>Hobbes:</dt>
        <dd>
            <input type="text" name="hobby"/>
        </dd>
    </dl>
    <input type="submit" class="btn btn-orange" value="Edit">
</form>
</div>
</body>
</html>
