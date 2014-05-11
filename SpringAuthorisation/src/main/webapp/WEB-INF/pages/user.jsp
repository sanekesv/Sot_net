<%@ page import="com.springapp.mvc.MyLinkedList" %>
<%@ page import="com.springapp.mvc.AboutUser" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.swing.text.html.HTMLDocument" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.04.14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link href="css/registration.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
<%--${uzas}--%>
<%MyLinkedList aboutUser = (MyLinkedList) session.getAttribute("allUser");%>
<table>
    <caption>All Users</caption>
    <%Iterator i = aboutUser.iterator();%>
    <%while(i.hasNext()){%>
        <%AboutUser a = (AboutUser) i.next();%>

<%--<%for(AboutUser user : aboutUser){%>--%>
    <tr>
        <td><img src="../resources/img/<%=a.getImageName()%>" width="100" height="100"></td>
        <td><a href="/user?login=<%=a.getUsername()%>"><%=a.getUsername()%></a></td>
        <%if(!session.getAttribute("login").equals(a.getUsername())){%>
        <td><a href="/dialogue?to=<%=a.getUsername()%>">Send message</a> </td><%}else{%>
        <td><a>It's You.</a></td> <%}%>
    </tr>
<%}%>
</table>
</div>
</body>
</html>
