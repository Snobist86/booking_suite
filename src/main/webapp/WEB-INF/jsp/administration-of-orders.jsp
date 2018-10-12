<%--
  Created by IntelliJ IDEA.
  User: Snob
  Date: 10.10.2018
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Администрирование заказов</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/checkPreviewOrder" method="post">
    <p>Проверка заказов по их статусу</p>
    <p>Статус:
        <select name="id">
            <c:forEach var="statusOrder" items="${requestScope.statusOrderList}">
                <option value="${statusOrder.id}">${statusOrder.title}</option>
            </c:forEach>
        </select><br>
        <button type="submit">Найти</button>
    </p>
</form>

<c:if test="${requestScope.orderStatusId == 1}">
<%--<p${requestScope.order}></p>--%>
    <c:forEach var="previewOrder" items="${requestScope.previewOrders}">
        <p var="order" items="${requestScope.order}>${order.id}</p>
        <form action="${pageContext.request.contextPath}/AddOrder" method="get">

            <input type="hidden" name="id" value="${previewOrder.id}">
            <input type="hidden" name="user" value="${previewOrder.userId}">
            <input type="hidden" name="size" value="${previewOrder.suiteSizeId}">
            <input type="hidden" name="category" value="${previewOrder.suiteCategoryId}">
            <input type="hidden" name="price" value="${previewOrder.totalPrice}">

            <p><i>номер предзаказа: </i> ${previewOrder.id};
                <i>  имя клиента: </i> ${previewOrder.userName};</p>
            <p><i>размер номера: </i> ${previewOrder.suiteSizeName};
                <i>  категория: </i> ${previewOrder.suiteCategoryName};</p>
            <p><i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                <i>  итоговая цена: </i> ${previewOrder.totalPrice};</p>
            <p><i>Дата прибытия: </i> ${previewOrder.checkIn};
                <i>  Дата отъезда: </i> ${previewOrder.checkOut};
                <i>  Дата бронирования: </i> ${previewOrder.bookingDate};</p>
            <p><i>Комментарий: </i> ${previewOrder.comment}</p>
        </form>
    </c:forEach>
</c:if>
</body>
</html>
