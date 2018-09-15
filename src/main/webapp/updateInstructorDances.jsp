<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
        <form class="form-horizontal" id="multipleForm" action="#" method="post">
            <div style="float:right">
                <a class="btn btn-primary" href="#" role="button">Update My Dances</a>
            </div>
            <div class="col-sm-6 dancePanel">
                <div class="panel panel-default">
                    <div class="panel-heading">Dances I Teach</div>
                    <div class="scrollbar scrollbar-deep-blue">
                        <div class="force-overflow">
                            <table class="table">
                                <tr><th>Dance</th><th>Check to Delete a Dance</th></tr>
                                <tr><td>E.C. Swing</td><td><input type="checkbox"></td></tr>
                                <tr><td>Waltz</td><td><input type="checkbox"></td></tr>
                                <tr><td>Salsa</td><td><input type="checkbox"></td></tr>
                                <tr><td>Tango</td><td><input type="checkbox"></td></tr>
                                <tr><td>Cha Cha</td><td><input type="checkbox"></td></tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="btn btn-primary" type="submit" class="btn btn-default">Delete</button>
                    <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                </div>
            </div>
        </form>
        <form class="form-horizontal" id="multipleForm" action="#" method="post">
            <div class="col-sm-6 dancePanel">
                <div class="panel panel-default">
                    <div class="panel-heading">Add a New Dance</div>
                    <div class="scrollbar scrollbar-deep-blue">
                        <div class="force-overflow">
                            <table class="table">
                                <tr><th>Dance</th><th>Check to Add a Dance</th><th>Experience (years)</th></tr>
                                <tr><td>E.C. Swing</td><td><input type="checkbox" name="addDance" value="danceId"></td><td><input type="text" name="addDanceExperience" placeholder="1"></td></tr>
                                <tr><td>Waltz</td><td><input type="checkbox" name="addDance" value="danceId"></td><td><input type="text" name="addDanceExperience" placeholder="1"></td></tr>
                                <tr><td>Salsa</td><td><input type="checkbox" name="addDance" value="danceId"></td><td><input type="text" name="danceExperience" placeholder="1"></td></tr>
                                <tr><td>Tango</td><td><input type="checkbox" name="addDance" value="danceId"></td><td><input type="text" name="danceExperience" placeholder="1"></td></tr>
                                <tr><td>Cha Cha</td><td><input type="checkbox" name="addDance" value="danceId"></td><td><input type="text" name="danceExperience" placeholder="1"></td></tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="btn btn-primary" type="submit" class="btn btn-default">Add</button>
                    <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                </div>
            </div>
        </form>

        <form class="form-horizontal" id="multipleForm" action="#" method="post">
            <div class="col-sm-6 dancePanel">
                <div class="panel panel-default">
                    <div class="panel-heading">Dance Experience</div>
                    <div class="scrollbar scrollbar-deep-blue">
                        <div class="force-overflow">
                            <table class="table">
                                <tr><th>Dance</th><th>Experience (years)</th></tr>
                                <tr><td>E.C. Swing</td><td><input type="text" name="danceExperience" placeholder="1"></td></tr>
                                <tr><td>Waltz</td><td><input type="text" name="danceExperience" placeholder="2"></td></tr>
                                <tr><td>Salsa</td><td><input type="text" name="danceExperience" placeholder="3"></td></tr>
                                <tr><td>Tango</td><td><input type="text" name="danceExperience" placeholder="4"></td></tr>
                                <tr><td>Cha Cha</td><td><input type="text" name="danceExperience" placeholder="5"></td></tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="btn btn-primary" type="submit" class="btn btn-default">Update</button>
                    <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                </div>
            </div>
        </form>
</div>
</div>
</body>
</html>