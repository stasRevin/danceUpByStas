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
            <c:choose>
                <c:when test="${not empty user.photoName}">
                    <img id="userPhoto" src="images/userPhotos${user.id}/${user.photoName}">
                </c:when>
                <c:otherwise>
                    <img id="userPhoto" src="images/nopic.jpg">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-sm-9 tabView">
            <ul class="nav nav-pills">
                <li class="active">
                    <a data-toggle="pill" href="#pills-upcomingLessons">Upcoming Lessons</a>
                </li>
                <li>
                    <a data-toggle="pill" href="#pills-progress">Progress by Dance</a>
                </li>

            </ul>
            <div class="tab-content">
                <div class="tab-pane fade" id="pills-progress">
                    <br/>
                    <c:forEach var="userDance" items="${userDances}">
                        <div class="progress">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:${userDance.learningProficiency}%">
                                    ${userDance.dance.name} ${userDance.learningProficiency}%
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="table tab-pane fade in active" id="pills-upcomingLessons">
                    <br/>
                    <jsp:include page="upcomingLessons.jsp"/>
                </div>
            </div>
            <br/><br/>
        </div>
    </div>
</div>
</body>
</html>