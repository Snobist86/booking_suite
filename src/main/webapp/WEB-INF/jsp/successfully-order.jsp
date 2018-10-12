<%--
  Created by IntelliJ IDEA.
  User: Snob
  Date: 08.10.2018
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Успешное бронирование</title>
</head>
<body>
<p>Ура! Вы забронировали номер. Теперь наши администраторы рассмотрять
вашу запрос и подберут конкретный номер для Вас. Проверьте вкладку "Мои заявки",
там будут отображаться ваши одобренные заявки, после этого вы должны оплатить
счёт за номер и процедура бронирования будет завершена.</p>

<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">вернуться на главную</button>
</form>
</body>
</html>
