<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
            <div style="float:right">
                <a class="btn btn-primary" href="instructorProfileUpdate.jsp" role="button">Update My Profile</a>
            </div>
        <br/><br/>
        <div style="float:right">
            <a class="btn btn-primary" href="/updateInstructorSchedule.jsp" role="button">Update My Schedule</a>
        </div>
        <div class="col-sm-12">
            <table class="table">
                <thead>
                    <tr><th>Dance Name</th><th>Experience (years)</th><th></th><th></th></tr>
                </thead>
                <tbody>
                <c:set var="counter" value="0" scope="page" />
                    <c:forEach var="userDance" items="${userDances}">
                        <c:set var="counter" value="${counter + 1}" scope="page"/>
                        <tr>
                            <td>${userDance.dance.name}</td>
                            <td>${userDance.yearsOfExperience}</td>
                            <td><span class="deleteDance" data-delete="${userDance.dance.name}">Delete</span></td>
                        </tr>
                    </c:forEach>

                </tbody>


            </table>
        </div>
        <jsp:include page="footer.jsp"/>
</div>
</div>
</body>
</html>

<script>
    $(document).ready(function() {

       $(".deleteDance").click(function() {

           $(this).closest("tr").remove();
           var thisClass = $(this);
           var danceName = thisClass.data("delete");
            console.log("dance name: " + danceName);
           $.get("http://localhost:8080/danceup/deleteUserDance?name=" + danceName, function () {});

       });

    });

</script>