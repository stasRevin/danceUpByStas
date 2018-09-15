<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div class="col-sm-3">
           <img id="userPhoto" src="images/userPhotos/rabbit.jpg">
        </div>
        <div class="col-sm-7">
            <h2>Progress by dance:</h2>
        </div>
        <div class="scrollbar scrollbar-deep-blue col-sm-7">
            <div class="force-overflow">
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                        E.C. Swing 40%
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:60%">
                        Waltz 60%
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                        Tango 80%
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-3">
            <h4>
                Username: rabbit4ever
            </h4>
            <h4>
                First name: Rabbit
            </h4>
            <h4>
                Last name: Quick
            </h4>
        </div>

        <div class="col-sm-7">
            <table>
                <tr>
                    <th>Upcoming Lessons</th>
                </tr>

            </table>
        </div>
    </div>
</div>
</body>
</html>