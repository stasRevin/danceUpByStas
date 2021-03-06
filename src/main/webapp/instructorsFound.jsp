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
        <form action="/danceup/searchInstructors" method="get">
            <div class="form-group">
                <label for="zipCode">Zip Code</label>
                <input type="text" class="form-control" id="zipCode" name="zipCode" aria-describedby="emailHelp" placeholder="Zip code (5 digits)">
            </div>
            <div class="form-group">
                <label for="radius">Distance (number of miles)</label>
                <input id="radius" name = "radius" type="text" class="form-control" placeholder="Number of miles">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Search Instructors</button>
        </form>
        <h3>Instructors Near Me</h3>

        <c:if test="${not empty zipRadiusServiceError}"><h4 class="error">${zipRadiusServiceError}</h4></c:if>
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
                    Book
                </th>
                <th>
                    Locations
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="instructor" items="${usersFound}">
                <tr>
                    <c:choose>
                        <c:when test="${not empty instructor.photoName}">
                            <td><img id="userPhotoClass" src="images/usersFoundPhotos${user.id}/${instructor.photoName}"></td>
                        </c:when>
                        <c:otherwise>
                            <td><img id="userPhotoClass" src="images/nopic.jpg"></td>
                        </c:otherwise>
                    </c:choose>
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

                        <a href="http://18.219.182.38:8080/danceup/bookLesson?instructorId=${instructor.id}" class="btn btn-info" role="button">Book a Lesson</a>
                    </td>
                    <td>
                        <ul>
                            <c:forEach var="location" items="${instructor.locations}">
                                <li>${location.name}, ${location.address1} ${location.city}, ${location.state} ${location.postalCode}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>