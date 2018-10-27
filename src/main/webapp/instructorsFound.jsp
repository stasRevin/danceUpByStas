<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="searchMyInstructors.jsp" role="button">Search Again</a>
        </div>
        <br/><br/>
        <h3>Instructors Found</h3>
        <table class="table">
            <thead>
            <tr>
                <th class="th-sm">
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">First Name
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Last Name
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Dances Taught
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">City
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">State
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Zip Code
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Book
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="instructor" items="${usersFound}">
                <tr>
                    <td><img class="userPhotoClass" src="images/userPhotos/${instructor.photoName}"></td>
                    <td>${instructor.firstName}</td>
                    <td>${instructor.lastName}</td>
                    <td>
                        <a href="http://localhost:8080/danceup/bookLesson?${instructor.id}" class="btn btn-info" role="button">Book a Lesson</a>
                    </td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>
    <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>