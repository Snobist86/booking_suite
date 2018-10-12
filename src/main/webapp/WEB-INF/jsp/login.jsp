<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/login" method="post">
    <fmt:message key="login.username"/> : <input type="text" name="login"><br>
    <fmt:message key="login.password"/> : <input type="password" name="password"><br>
    <input type="submit" value="<fmt:message key="login.submit"/>">
</form>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <button type="submit"><fmt:message key="login.registration"/></button>
</form>
</body>
</html>
