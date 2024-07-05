<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.06.2024
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Create A User</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/user/create" method="post">
    <label for="id">User ID:</label>
    <input type="text" id="id" name="id" value="${id}" required><br><br>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email" value="${email}" required
           pattern="^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$"><br><br>
    <label for="login">Login:</label>
    <input type="text" id="login" name="login" value="${login}" required><br><br>
    <button type="submit">Create a User</button>
</form>
</body>
