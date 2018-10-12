<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Создание гостиничного номера</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createSuite" method="post">
    Номер:
    <input type="number" name="number"/><br>
    Вместительность:
    <select name="size">
        <c:forEach var="size" items="${requestScope.sizes}">
            <option value="${size.id}">${size.name}</option>
        </c:forEach>
    </select><br>
    Категория:
    <select name="category">
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>
    Цена:
    <input type="number" name="price"/><br>
    Этаж:
    <input type="number" name="floor"/><br>
    <input type="submit" value="внести в базу">
</form>
<form action="${pageContext.request.contextPath}/create" method="get">
    <button type="submit">на главную страницу админа</button>
</form>
</body>
</html>
