<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
        <div class="col-sm-12">
            <h3>
                ${user.firstName} ${user.lastName}
            </h3>
        </div>
        <div class="col-sm-3">
            <img id="userPhoto" src="images/userPhotos/${user.photoName}">
        </div>

        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading">Work Info</div>
                <div class="scrollbar scrollbar-deep-blue">
                    <div class="force-overflow">
                        <table class="table">
                            <h4>Student/Lesson Details</h4>
                            <tr><td>Total Lessons Taught</td><td>78</td></tr>
                            <tr><td>Number of Active Students</td><td>9</td></tr>
                            <tr><td>Current rate per Lesson</td><td>$${user.payRate}</td></tr>
                        </table>
                        <h4>General Availability</h4>
                        <table class="table">
                            <tr><td>Mondays</td><td>5PM-8PM</td></tr>
                            <tr><td>Tuesdays</td><td>5PM-10PM</td></tr>
                            <tr><td>Wednesday</td><td>5PM-10PM</td></tr>
                            <tr><td>Thursday</td><td>5PM-9PM</td></tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading">Teaching Experience</div>
                <div class="scrollbar scrollbar-deep-blue">
                    <div class="force-overflow">
                        <table class="table">
                        <c:forEach var="userDance" items="${userDances}">
                            <tr><td>${userDance.dance.name}</td><td>${userDance.yearsOfExperience} years</td></tr>
                        </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="scrollbar scrollbar-deep-blue col-sm-9">
            <div class="force-overflow">
                <table class="table">
                    <tr>
                        <th>#</th>
                        <th>Upcoming Lessons</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Example</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Example</td>
                    </tr>
                </table>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>