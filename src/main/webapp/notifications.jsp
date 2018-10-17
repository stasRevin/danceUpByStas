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
        <jsp:include page="instructorNavbar.jsp"></jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="studentNavbar.jsp"></jsp:include>
    </c:otherwise>
</c:choose>
    </div>
    <h1>Notifications</h1>
    <div class="col-sm-12">
        <table class="table">
            <tr><td>A lesson was booked with Jim @ 5PM on 09/02/2018 at 123 Main St. Madison, 53704 </td></tr>
            <tr><td>A lesson was booked with Jim @ 5PM on 09/03/2018 at 123 Main St. Madison, 53704 </td></tr>
        </table>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>