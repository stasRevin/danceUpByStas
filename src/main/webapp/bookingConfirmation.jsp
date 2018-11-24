<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="searchMyInstructors.jsp" role="button">Search Instructors</a>
        </div>
        <br/><br/>
        <div style="float:right">
            <form action="/danceup/bookLesson" method="get">
                <input type="hidden" name="instructorId" value="${instructor.id}">
                <input class="btn btn-primary" type="submit" value="Book Another Lesson With ${instructor.firstName}">
            </form>
        </div>
        <div class="col-sm-12">
            <table class="table">
                <tr><td>Instructor</td><td>${instructor.firstName} ${instructor.lastName}</td></tr>
                <tr><td>Date</td><td>${lesson.date}</td></tr>
                <tr><td>Time</td><td>${lesson.startTime}</td></tr>
                <tr><td>Duration</td><td>1 hour(s)</td></tr>
                <tr><td>Location</td><td>${location.name} ${location.address1} ${location.city} ${location.state} ${location.postalCode}</td></tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>