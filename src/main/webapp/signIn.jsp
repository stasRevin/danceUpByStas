<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="navbar.jsp"/>
        <form class="form-horizontal" id="multipleForm" action="/danceup/signInUser" method="post">
            <div class="form-group">
                <label class="control-label col-sm-3">Sign up as</label>
                <div class="col-sm-9">
                    <input type="radio" name="role" value="student"> Student
                    &nbsp;&nbsp;
                    <input type="radio" name="role" value="instructor"> Dance Instructor
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Username</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="username">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Password</label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-5">
                    <button class="btn btn-primary" type="submit" class="btn btn-default">Submit</button>
                    <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                </div>
            </div>
        </form>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>