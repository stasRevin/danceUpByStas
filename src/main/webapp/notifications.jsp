<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <c:choose>
            <c:when test="${role == 1}">
                <jsp:include page="instructorNavbar.jsp"/>
            </c:when>
            <c:when test="${role == 2}">
                <jsp:include page="studentNavbar.jsp"/>
            </c:when>
        </c:choose>
    </div>
    <div class="col-sm-12">
        <table class="table">
            <thead>
                <th>Notification</th>
            </thead>
            <tbody>
                <c:forEach var="notification" items="${notificationRequest}">
                    <tr>
                        <td>
                            ${notification.dateTime}: ${notification.message}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>