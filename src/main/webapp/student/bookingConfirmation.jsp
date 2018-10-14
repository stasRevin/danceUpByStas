<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="searchMyInstructors.jsp" role="button">Search Instructors</a>
        </div>
        <br/><br/>
        <div style="float:right">
            <form action="" method="post">
                <input type="hidden" value="instructorId">
                <input class="btn btn-primary" type="submit" value="Book Another Lesson With Dancing Bobby ${instructor.FirstName}">
            </form>
        </div>
        <div class="col-sm-12">
            <table class="table">
                <tr><td>Instructor</td><td>Dancing Bobby</td></tr>
                <tr><td>Date</td><td>09/02/2018</td></tr>
                <tr><td>Time</td><td>5PM</td></tr>
                <tr><td>Duration</td><td>1 hour(s)</td></tr>
                <tr><td>Location</td><td>123 Main St. Madison, WI 53704</td></tr>
            </table>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>