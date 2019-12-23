<div>
    <div class="panel-heading"><span class="lead">Search cars</span></div>
    <div class="searcher-form">
        <form form:action="@{/cars}" method="get">
            <input type="text" placeholder="Enter reg no" class="custom-input" name="regNo" id="regNo" form:value="${regNo}"/>
            <input type="submit" class="btn btn-primary" value="Search car">
            <sec:authorize access="hasRole('ADMIN')">
                <a class="btn btn-primary" href="<c:url value='/newcar' />"><spring:message code="add.new.car"/></a>
            </sec:authorize>
        </form>

    </div>
    <div>
        <c:if test="${not empty findcar}">
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
            <tr>
                <td>${findcar.carType.mark}</td>
                <td>${findcar.carType.model}</td>
                <td>${findcar.year}</td>
                <td>${findcar.regNo}</td>
            </tr>
            </tbody>
        </table>
        </c:if>
    </div>
</div>