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
        <h3>Instructors Found</h3>
        <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th class="th-sm">First Name
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Last Name
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Dances Taught
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">City
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">State
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Zip Code
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
                <th class="th-sm">Book
                    <i class="fa fa-sort float-right" aria-hidden="true"></i>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Tiger</td>
                <td>Nixon</td>
                <td>E.C. Swing, Waltz, Tango</td>
                <td>Madison</td>
                <td>WI</td>
                <td>53704</td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="studentId" value="id">
                        <button type="submit" class="btn btn-primary btn-default">Book</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Maria</td>
                <td>Gonzalez</td>
                <td>Waltz, Cha Cha, Rumba</td>
                <td>Madison</td>
                <td>WI</td>
                <td>53704</td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="studentId" value="id">
                        <button type="submit" class="btn btn-primary btn-default">Book</button>
                    </form>
                </td>
            </tr>
            </tfoot>
        </table>
    <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>