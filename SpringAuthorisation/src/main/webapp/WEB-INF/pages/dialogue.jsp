<%@ page import="com.springapp.mvc.StructureMessage" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 07.05.14
  Time: 2:15
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

    <h3>Messages</h3>

    <form action="/addmessage" method="post">

        <p><textarea name="message" cols="45" rows="3" placeholder="Enter your message here" required></textarea></p>

        <p><input type="submit" value="Send a message"></p>
    </form>
    <table>
        <tr>
            <th>Sender</th>
            <th>Message</th>
            <th>Date</th>
        </tr>
        <%LinkedList<StructureMessage> set = (LinkedList<StructureMessage>) session.getAttribute("messages");%>
        <%for (StructureMessage i : set) {%>
        <tr>
            <td><%=i.getFrom()%>
            </td>
            <td><%=i.getMessage()%>
            </td>
            <td><%=i.getDate()%>
            </td>
        </tr>
        <%}%>

    </table>
</div>

</body>
</html>
