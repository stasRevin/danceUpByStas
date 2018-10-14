<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div class="col-sm-12">
            <h3>
                Rabbit Quick
            </h3>
        </div>
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