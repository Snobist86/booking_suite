<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateSomething</title>
</head>
<body>
<h2>Что нового вы хотите внести в базу данных?</h2>

<form action="${pageContext.request.contextPath}/createSuiteCategory" method="get">
    <button type="submit">Категорию номера</button>
</form>

<form action="${pageContext.request.contextPath}/createSuiteSize" method="get">
    <button type="submit">Размер номера</button>
</form>

<form action="${pageContext.request.contextPath}/createSuite" method="get">
    <button type="submit">Номер</button>
</form>

<form action="${pageContext.request.contextPath}/changeRole" method="get">
    <button type="submit">Изменить статус пользователя</button>
</form>

<form action="${pageContext.request.contextPath}/checkPreviewOrder" method="get">
    <button type="submit">администрирование запросов</button>
</form>

<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">вернуться на главную</button>
</form>
</body>
</html>
