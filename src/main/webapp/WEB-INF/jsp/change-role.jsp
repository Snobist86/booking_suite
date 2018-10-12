<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/changeRole" method="post">
    Пользователь: <br>
    <input type="text" name="login"/> <br>
    Статус: <br>
    <select name="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="изменить статус"><br>
</form>
<form action="${pageContext.request.contextPath}/create" method="get">
    <button type="submit">на главную страницу админа</button>
</form>
</body>
</html>
