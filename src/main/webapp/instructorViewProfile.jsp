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
        <br/>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading">Work Info</div>
                <div class="scrollbar scrollbar-deep-blue">
                    <div class="force-overflow">
                        <table class="table">
                            <h4>Student/Lesson Details</h4>
                            <tr><td>Total Lessons Taught</td><td>${lessonsTaughtCount}</td></tr>
                            <tr><td>Current rate per Lesson</td><td>$${user.payRate}</td></tr>
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

        <!-- https://mdbootstrap.com/content/bootstrap-table-pagination/ -->
        <div class="col-sm-9">
            <h3>Upcoming Lessons</h3>
            <div class="table-responsive">
                <table class="table dataTable table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Location Name</th>
                        <th>Location Address</th>
                        <th>Location City</th>
                        <th>Location State</th>
                        <th>Location Zip</th>
                    </tr>
                    </thead>
                    <c:forEach var="userLesson" items="${userLessons}">
                        <tr><td>${userLesson.lesson.date}</td><td>${userLesson.lesson.startTime}</td>
                            <td>${userLesson.lesson.endTime}</td>
                            <td>${userLesson.lesson.location.name}</td>
                            <td>${userLesson.lesson.location.address1}</td>
                            <td>${userLesson.lesson.location.city}</td>
                            <td>${userLesson.lesson.location.state}</td>
                            <td>${userLesson.lesson.location.postalCode}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="col-sm-9">
            <h3>Availability</h3>
        </div>
<!-- https://mdbootstrap.com/content/bootstrap-table-pagination/ -->
            <div class="table col-sm-9">

                    <table class="dataTable display" cellspacing="0" width="100%">
                        <thead>
                            <tr><th>Date</th><th>Start Time</th><th>End Time</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach var="schedule" items="${schedules}">
                                <tr><td>${schedule.date}</td><td>${schedule.startTime}</td><td>${schedule.endTime}</td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>

