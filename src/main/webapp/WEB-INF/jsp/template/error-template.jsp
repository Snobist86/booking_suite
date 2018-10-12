<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <p>${error}</p>
        </c:forEach>
    </div>
</c:if>
