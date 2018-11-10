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
        <div class="col-sm-9">
            <div class="col-sm-3">
                <img id="userPhoto" src="images/userPhotos${user.id}/${user.photoName}">
            </div>
        </div>
        <br/>
        <div class="col-sm-9">
            <h4>Upcoming Lessons</h4>
            <jsp:include page="upcomingLessons.jsp"/>
        </div>

        <div class="col-sm-9 tabView">
            <ul class="nav nav-pills">
                <li>
                    <a data-toggle="pill" href="#pills-workInfo">Work Info</a>
                </li>
                <li>
                    <a data-toggle="pill" href="#pills-experience">Teaching Experience</a>
                </li>
                <li class="active">
                    <a data-toggle="pill" href="#pills-availability">Availability</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade" id="pills-workInfo">
                    <br/>
                    <table class="table">
                        <tr><td>Total Lessons Taught</td><td>${lessonsTaughtCount}</td></tr>
                        <tr><td>Current rate per Lesson</td><td>$${user.payRate}</td></tr>
                    </table>
                </div>

                <div class="table tab-pane fade" id="pills-experience">
                    <br/>
                    <table class="table">
                        <c:forEach var="userDance" items="${userDances}">
                            <tr><td>${userDance.dance.name}</td><td>${userDance.yearsOfExperience} years</td></tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="table tab-pane fade in active" id="pills-availability">
                    <br/>
                    <div class="col-sm-9">
                        <jsp:include page="instructorAvailability.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/><br/>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>

