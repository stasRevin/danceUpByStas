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
            <a class="btn btn-primary" href="/danceup/searchInstructors" role="button">Search Again</a>
        </div>
        <br/><br/>
        <h1>Book a Lesson With</h1>
        <div class="col-sm-12">
            <h3>
                ${instructor.firstName} ${instructor.lastName}
            </h3>
        </div>
        <div class="col-sm-3">
            <img id="userPhoto" src="images/userPhotos/${instructor.photoName}">
        </div>
        <br/>
        <div class="col-sm-9 tabView">
            <ul class="nav nav-pills">
                <li class="active">
                    <a data-toggle="pill" href="#pills-experience">Teaching Experience</a>
                </li>
                <li>
                    <a data-toggle="pill" href="#pills-availability">Availability</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="table tab-pane fade in active" id="pills-experience">
                    <br/>
                    <table class="table">
                        <c:forEach var="instructorDance" items="${instructor.dances}">
                            <tr><td>${instructorDance.dance.name}</td><td>${instructorDance.yearsOfExperience} years</td></tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="table tab-pane fade" id="pills-availability">
                    <br/>
                    <div class="col-sm-9">
                        <jsp:include page="instructorAvailability.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <h3 class="col-sm-12">Set Your Lesson Details</h3>
    <br/>
        <div class="col-sm-12">
            <form  class="form-horizontal" id="multipleForm" action="#" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-3">Date</label>
                    <div class="col-sm-6">
                        <input id="lessonDate" type="text" class="datepicker" data-instructorId="${instructor.id}">
                    </div>
                </div>
                <!-- User JS onchange event to handle availability display.-->
                <div class="form-group">
                    <label class="control-label col-sm-3">Time</label>
                    <div class="col-sm-6">
                        <select name="lessonTime">
                            <option>None Selected</option>
                            <option>5PM</option>
                            <option>6PM</option>
                            <option>8PM</option>
                        </select>
                    </div>
                </div>
                <!-- Use web service https://www.promaptools.com/#zips-inside-radius here to find a location within 5 miles from student's home -->
                <div class="form-group">
                    <label class="control-label col-sm-3">Dance Studio</label>
                    <div class="col-sm-6">
                        <select name="lessonTime">
                            <option>None Selected</option>
                            <c:forEach var="location" items="${instructor.locations}">
                                <option>${location.name} ${location.address1} ${location.city}, ${location.state} ${location.postalCode}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-6">
                        <button class="btn btn-primary" type="submit" class="btn btn-default">Book</button>
                        <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                    </div>
                </div>
            </form>
            <jsp:include page="footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {

        var lessonDate = $("#lessonDate");
        var instructorId = lessonDate.attr("data-instructorId");

        lessonDate.change(function () {

            console.log(lessonDate.val());
            console.log(instructorId);
        });
    });
</script>