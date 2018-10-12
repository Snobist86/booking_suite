<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Создание категории</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createSuiteCategory" method="post">
    Название категории: <input type="text" name="name"/><br>
    Описание:<input type="text" name="comment" placeholder="не более 512 символов"/><br>
    <input type="submit" value="внести в базу">
</form>
<form action="${pageContext.request.contextPath}/create" method="get">
    <button type="submit">на главную страницу админа</button>
</form>
</body>
</html>
