<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>

            <form action="/danceup/insertInstructorLocation" method="post">
                <div class="form-group">
                    <label for="locationSelection">Add a Location</label>
                    <select class="form-control" name="id" id="locationSelection">
                        <c:forEach var="location" items="${allLocations}">
                            <option value="${location.id}">${location.name}, ${location.address1}, ${location.city}, ${location.state}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Add Location</button>
            </form>
            <table class="table">
                <thead>
                    <tr><th>Name</th><th>Address</th><th>City</th><th>State</th><th>Zip Code</th><th></th><th></th></tr>
                </thead>
                <tbody>
                    <c:forEach var="location" items="${locations}">
                        <tr>
                            <td>${location.name}</td>
                            <td>${location.address1}</td>
                            <td>${location.city}</td>
                            <td>${location.state}</td>
                            <td>${location.postalCode}</td>
                            <td><span class="deleteLocation" data-delete="${location.id}"><img id="trashCan" src="images/trashCan.png" alt="trash can image"></span></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp"/>
</div>
</div>
</body>
</html>