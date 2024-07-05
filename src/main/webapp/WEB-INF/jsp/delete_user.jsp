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
    <title>Delete User</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/user/delete" method="post">
    <label for="id">User ID:</label>
    <input type="text" id="id" name="id" value="${id}" required><br><br>
    <button type="submit">Delete User</button>
</form>
</body>

</html>
