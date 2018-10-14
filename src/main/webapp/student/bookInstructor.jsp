<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="searchMyInstructors.jsp" role="button">Search Again</a>
        </div>
        <br/><br/>
        <h1>Book a Lesson With</h1>
        <div class="col-sm-12">
            <h3>
                Dancing Bobby
            </h3>
        </div>
        <div class="col-sm-3">
            <img id="userPhoto" src="images/userPhotos/dancing.jpg" style="margin-bottom: 5%;">
        </div>

        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading">Work Info</div>
                <div class="scrollbar scrollbar-deep-blue">
                    <div class="force-overflow">
                        <h4>Lesson Details</h4>
                        <table class="table">
                            <tr><td>Lesson Duration</td><td>1 hour</td></tr>
                            <tr><td>Current rate per Lesson</td><td>50$</td></tr>
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
                            <tr><td>E.C. Swing</td><td>5 years</td></tr>
                            <tr><td>Waltz</td><td>8 years</td></tr>
                            <tr><td>Salsa</td><td>4 years</td></tr>
                            <tr><td>Tango</td><td>6 years</td></tr>
                            <tr><td>Cha Cha</td><td>10 years</td></tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <form  class="form-horizontal" id="multipleForm" action="#" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-3">Date</label>
                    <div class="col-sm-6">
                        <input type="text" class="datepicker">
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
                    <label class="control-label col-sm-3">Location Withing 5 Miles From Your Home</label>
                    <div class="col-sm-6">
                        <select name="lessonTime">
                            <option>None Selected</option>
                            <option>123 Main St. Madison, WI 53704</option>
                            <option>345 Main St. Madison, WI 53704</option>
                            <option>678 Main St. Madison, WI 53704</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" value="instructorid">
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