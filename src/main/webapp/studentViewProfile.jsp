<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="studentNavbar.jsp"/>
        <div class="col-sm-12">
            <h3>
                ${user.firstName} ${user.lastName}
            </h3>
        </div>
        <div class="col-sm-3">
            <img id="userPhoto" src="images/userPhotos/${user.photoName}">
        </div>
        <div class="col-sm-7">
            <h2>Progress by dance:</h2>
        </div>
        <div class="scrollbar scrollbar-deep-blue col-sm-7">
            <div class="force-overflow">

                    <c:forEach var="userDance" items="${userDances}">
                        <div class="progress">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:${userDance.learningProficiency}%">
                                    ${userDance.dance.name} ${userDance.learningProficiency}%
                            </div>
                        </div>
                    </c:forEach>

            </div>
        </div>

        <jsp:include page="upcomingLessons.jsp"/>
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>