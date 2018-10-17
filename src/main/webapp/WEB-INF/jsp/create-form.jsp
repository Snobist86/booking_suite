<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateSomething</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/createSuiteCategory" method="get">
    <button type="submit">Создать категорию номера</button>
</form>

<form action="${pageContext.request.contextPath}/createSuiteSize" method="get">
    <button type="submit">Создать размер номера</button>
</form>

<form action="${pageContext.request.contextPath}/createSuite" method="get">
    <button type="submit">Создать номер</button>
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
