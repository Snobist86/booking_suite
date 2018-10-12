<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Поиск и бронирование</title>
</head>
<body>
<h1>Поиск номера</h1>
<form action="${pageContext.request.contextPath}/searchSuite" method="post">

    Количество человек:
    <select name="size">
        <option value="">${"любое количество"}</option>
        <c:forEach var="size" items="${requestScope.sizes}">
            <option value="${size.id}">${size.name} </option>
        </c:forEach>
    </select><br>

    Категория:
    <select name="category">
        <option value="">${"любая категория"}</option>
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>

    Дата заселения <input class="window" type="date" name="startReservationDate" placeholder="yyyy-MM-dd"
                          value="${not empty requestScope.defaultStartDate ? requestScope.defaultStartDate : param.startReservationDate}"><br>
    Дата выселения <input class="window" type="date" name="endReservationDate" placeholder="yyyy-MM-dd"
                          value="${not empty requestScope.defaultEndDate ? requestScope.defaultEndDate : param.endReservationDate}">

    <button type="submit">Найти</button>
    <br>

    <%@include file="/WEB-INF/jsp/template/error-template.jsp" %>
</form>

<c:forEach var="vacantSuites" items="${requestScope.vacantSuites}">
    <form action="${pageContext.request.contextPath}/addPreviewOrder" method="post">
        <input type="hidden" name="inDate"
               value="${not empty requestScope.defaultStartDate ? requestScope.defaultStartDate : param.startReservationDate}">
        <input type="hidden" name="outDate"
               value="${not empty requestScope.defaultEndDate ? requestScope.defaultEndDate : param.endReservationDate}">
        <input type="hidden" name="suiteSizeId" value="${vacantSuites.suiteSizeId}">
        <input type="hidden" name="suiteCategoryId" value="${vacantSuites.suiteCategoryId}">
        <input type="hidden" name="price" value="${vacantSuites.price}">

        <p>Размер номера: ${vacantSuites.suiteSize}</p>
        <p>Категория номера: ${vacantSuites.suiteCategory}</p>
        <p>Цена: ${vacantSuites.price}$</p>
        <p>Комментарий:<input type="text" name="comment" placeholder="не более 256 символов"/></p>
        <details>
            <summary>Описание номера</summary>
            <p>
                    ${vacantSuites.commentSize}
                    ${vacantSuites.commentCategory}
            </p>
        </details>
        <p>
            <button type="submit">Забронировать</button>
        </p>
        <br>
    </form>
</c:forEach>

<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">вернуться на главную</button>
</form>
</body>
</html>