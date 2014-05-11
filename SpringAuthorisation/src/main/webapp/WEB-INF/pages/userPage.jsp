<%@ page import="com.springapp.mvc.InfoUser" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 05.05.14
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<img src="./resources/img/${pageImage}" width="300" height="500">
    <%InfoUser infoUser = (InfoUser) session.getAttribute("infoThisUser");%>  <br>
    First Name : <%=infoUser.getFirstname()%><br>
    Last Name : <%=infoUser.getLastname()%><br>
    Birth Date : <%=infoUser.getBdate()%><br>
    Hobbes : <%=infoUser.getHobby()%>        <br>         <br>

    <c:if test="${myPage == 'true'}">
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br />
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>                      <br>
        <a href="/editprofile">Edit profile</a>
    </c:if>
    <%if(!session.getAttribute("myPage").equals("true")){%>
    <a href="/dialogue?to=<%=session.getAttribute("thisUser")%>">Send message</a><%}%>
</div>
</body>
</html>
