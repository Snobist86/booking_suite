<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание размера номера</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createSuiteSize" method="post">
    Название размера: <input type="text" name="name"/><br>
    Максимальная вместительность: <input type="text" name="maxCapacity"><br>
    Описание:<input type="text" name="comment" placeholder="не более 512 символов"/><br>
    <input type="submit" value="внести в базу">
</form>
<form action="${pageContext.request.contextPath}/create" method="get">
    <button type="submit">на главную страницу админа</button>
</form>
</body>
</html>
