<%@include file="head.jsp" %>
<body id="home">
<%@include file="authheader.jsp" %>
<div class="lang">
    <a href="${pageContext.request.contextPath}/cars?lang=ru"><img src="/static/img/ru.png"></a>
    <a href="${pageContext.request.contextPath}/cars?lang=en"><img src="/static/img/en.png"></a>
</div>
<section id="about">
    <div class="container">
        <%@include file="searchcars.jsp" %>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of cars</span></div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Mark</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Registration number</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars}" var="car">
                    <c:if test="${car.available == true}">
                    <tr>
                        <td>${car.carType.mark}</td>
                        <td>${car.carType.model}</td>
                        <td>${car.year}</td>
                        <td>${car.regNo}</td>
                        <sec:authorize access="hasAnyRole('USER')">
                            <td width="80px"><a href="<c:url value='/rent-car-${car.regNo}' />" class="btn btn-primary"><spring:message code="rent"/></a></td>
                        </sec:authorize>
                        <sec:authorize access="!hasAnyRole('ADMIN', 'DEALER','USER')">
                            <td width="80px"><a href="<c:url value='/login' />" class="btn btn-danger"><spring:message code="login.to.more"/></a></td>
                        </sec:authorize>

                        <sec:authorize access="hasAnyRole('ADMIN', 'DEALER')">
                            <td width="80px"><a href="<c:url value='/delete-car-${car.regNo}' />" class="btn btn-primary"><spring:message code="delete"/></a></td>
                        </sec:authorize>

                    </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </div>
</section><!--/#about-->
<%@include file="footer.jsp" %>
</body>
</html>
