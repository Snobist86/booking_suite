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

<c:forEach var="previewOrder" items="${requestScope.previewOrders}">
    <c:if test="${previewOrder.orderStatusId eq '1'}">
        <fieldset>
            <form action="${pageContext.request.contextPath}/createOrder" method="get">

                <input type="hidden" name="id" value="${previewOrder.id}">
                <input type="hidden" name="userId" value="${previewOrder.userId}">
                <input type="hidden" name="userLogin" value="${previewOrder.userName}">
                <input type="hidden" name="size" value="${previewOrder.suiteSizeId}">
                <input type="hidden" name="category" value="${previewOrder.suiteCategoryId}">
                <input type="hidden" name="checkIn" value="${previewOrder.checkIn}">
                <input type="hidden" name="checkOut" value="${previewOrder.checkOut}">
                <input type="hidden" name="price" value="${previewOrder.totalPrice}">

                <p><i>номер предзаказа: </i> ${previewOrder.id};
                    <i> имя клиента: </i> ${previewOrder.userName};<br>
                    <i>размер номера: </i> ${previewOrder.suiteSizeName};
                    <i> категория: </i> ${previewOrder.suiteCategoryName};<br>
                    <i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                    <i> итоговая цена: </i> ${previewOrder.totalPrice};<br>
                    <i>Дата прибытия: </i> ${previewOrder.checkIn};
                    <i> Дата отъезда: </i> ${previewOrder.checkOut};
                    <i> Дата бронирования: </i> ${previewOrder.bookingDate};<br>
                    <i>Комментарий: </i> ${previewOrder.comment}</p>

                <button type="submit">подобрать номер</button>

            </form>
        </fieldset>
    </c:if>

    <c:if test="${previewOrder.orderStatusId eq '2'}">
        <fieldset>
            <form action="${pageContext.request.contextPath}/deleteOrder" method="post">

                <input type="hidden" name="id" value="${previewOrder.id}">

                <p><i>номер предзаказа: </i> ${previewOrder.id};
                    <i> имя клиента: </i> ${previewOrder.userName};<br>
                    <i>размер номера: </i> ${previewOrder.suiteSizeName};
                    <i> категория: </i> ${previewOrder.suiteCategoryName};<br>
                    <i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                    <i> итоговая цена: </i> ${previewOrder.totalPrice};<br>
                    <i>Дата прибытия: </i> ${previewOrder.checkIn};
                    <i> Дата отъезда: </i> ${previewOrder.checkOut};
                    <i> Дата бронирования: </i> ${previewOrder.bookingDate};<br>
                    <i>Комментарий: </i> ${previewOrder.comment}</p>

                <c:forEach var="statusOrder" items="${requestScope.statusOrderList}">
                    <c:if test="${statusOrder.id eq '7'}">
                        <input type="hidden" name="statusId" value="${statusOrder.id}">
                    </c:if>
                </c:forEach>

                <button type="submit">удалить заказ</button>

            </form>
        </fieldset>
    </c:if>

    <c:if test="${previewOrder.orderStatusId eq '3'}">
        <fieldset>
            <p><i>номер предзаказа: </i> ${previewOrder.id};
                <i> имя клиента: </i> ${previewOrder.userName};<br>
                <i>размер номера: </i> ${previewOrder.suiteSizeName};
                <i> категория: </i> ${previewOrder.suiteCategoryName};<br>
                <i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                <i> итоговая цена: </i> ${previewOrder.totalPrice};<br>
                <i>Дата прибытия: </i> ${previewOrder.checkIn};
                <i> Дата отъезда: </i> ${previewOrder.checkOut};
                <i> Дата бронирования: </i> ${previewOrder.bookingDate};<br>
                <i>Комментарий: </i> ${previewOrder.comment}</p>
        </fieldset>
    </c:if>

    <c:if test="${previewOrder.orderStatusId eq '4'}">
        <fieldset>
            <form action="${pageContext.request.contextPath}/deleteOrder" method="post">

                <input type="hidden" name="id" value="${previewOrder.id}">

                <p><i>номер предзаказа: </i> ${previewOrder.id};
                    <i> имя клиента: </i> ${previewOrder.userName};<br>
                    <i>размер номера: </i> ${previewOrder.suiteSizeName};
                    <i> категория: </i> ${previewOrder.suiteCategoryName};<br>
                    <i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                    <i> итоговая цена: </i> ${previewOrder.totalPrice};<br>
                    <i>Дата прибытия: </i> ${previewOrder.checkIn};
                    <i> Дата отъезда: </i> ${previewOrder.checkOut};
                    <i> Дата бронирования: </i> ${previewOrder.bookingDate};<br>
                    <i>Комментарий: </i> ${previewOrder.comment}</p>

                <c:forEach var="statusOrder" items="${requestScope.statusOrderList}">
                    <c:if test="${statusOrder.id eq '5'}">
                        <input type="hidden" name="statusId" value="${statusOrder.id}">${statusOrder.title}
                    </c:if>
                </c:forEach>

                <button type="submit">удалить заказ</button>

            </form>
        </fieldset>
    </c:if>

    <c:if test="${previewOrder.orderStatusId eq '7'}">
        <fieldset>
            <form action="${pageContext.request.contextPath}/deleteOrderAndPreviewOrder" method="post">

                <input type="hidden" name="id" value="${previewOrder.id}">

                <p><i>номер предзаказа: </i> ${previewOrder.id};
                    <i> имя клиента: </i> ${previewOrder.userName};<br>
                    <i>размер номера: </i> ${previewOrder.suiteSizeName};
                    <i> категория: </i> ${previewOrder.suiteCategoryName};<br>
                    <i>статус заказа: </i> ${previewOrder.orderStatusTitle};
                    <i> итоговая цена: </i> ${previewOrder.totalPrice};<br>
                    <i>Дата прибытия: </i> ${previewOrder.checkIn};
                    <i> Дата отъезда: </i> ${previewOrder.checkOut};
                    <i> Дата бронирования: </i> ${previewOrder.bookingDate};<br>
                    <i>Комментарий: </i> ${previewOrder.comment}</p>

                <button type="submit">удалить заказ и предзаказ</button>

            </form>
        </fieldset>
    </c:if>
</c:forEach>

<form action="${pageContext.request.contextPath}/checkPreviewOrder" method="get">
    <button type="submit">новый поиск</button>
</form>

<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">вернуться на главную</button>
</form>
</body>
</html>
