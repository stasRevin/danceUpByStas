<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
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
                <th class="th-sm">Favorite Dance
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
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Tiger</td>
                <td>Nixon</td>
                <td>E.C. Swing</td>
                <td>Madison</td>
                <td>WI</td>
                <td>53704</td>
            </tr>
            <tr>
                <td>Maria</td>
                <td>Gonzalez</td>
                <td>Waltz</td>
                <td>Madison</td>
                <td>WI</td>
                <td>53704</td>
            </tr>
            </tfoot>
        </table>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>