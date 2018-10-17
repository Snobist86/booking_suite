<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/registration" method="post">
    Username: <input type="text" name="login"><br>
    Password: <input type="password" name="password"><br>
    Email: <input type="text" name="eMail"><br>
    <button type="submit">Registration</button> <br>
</form>
<form action="${pageContext.request.contextPath}/login" method="get">
    <button type="submit">Log in</button>
</form>
</body>
</html>
