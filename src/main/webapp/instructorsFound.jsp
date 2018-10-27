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
        <h3>Instructors Near Me</h3>
        <table class="display responsive nowrap">
            <thead>
            <tr>
                <th>
                </th>
                <th>
                    First Name
                </th>
                <th>
                    Last Name
                </th>
                <th>
                    Dances Taught
                </th>
                <th>
                    Locations
                </th>
                <th>
                    Book
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
                        <ul>
                            <c:forEach var="dance" items="${instructor.dances}">
                                <li>${dance.dance.name}</li>
                            </c:forEach>

                        </ul>
                    </td>
                    <td>
                        <ul>
                            <c:forEach var="location" items="${instructor.locations}">
                                <li>${location.name}, ${location.address1} ${location.city}, ${location.state} ${location.postalCode}</li>
                            </c:forEach>
                        </ul>
                    </td>
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