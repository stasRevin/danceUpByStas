<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
<jsp:include page="instructorNavbar.jsp"/>
        <div class="col-sm-12">
            <h3>
                ${student.firstName} ${student.lastName}
            </h3>
        </div>
        <div class="col-sm-3">
            <img id="userPhoto" src="images/usersFoundPhotos${user.id}/${student.photoName}">
        </div>
        <div class="col-sm-6">
            <c:forEach var="studentDance" items="${student.dances}">
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:${studentDance.learningProficiency}%">
                            ${studentDance.dance.name} ${studentDance.learningProficiency}%
                    </div>
                </div>
            </c:forEach>
        </div>
        <br/>
        <div class="col-sm-9">
            <form action="/danceup/notifyUser" method="POST">
                <div class="form-group">
                    <label for="notification">Send notification to ${student.firstName}:</label>
                    <input type="text" class="form-control" name="message" id="notification">
                    <input type="hidden" name="recepientUserId" value="${student.id}">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <br/>
    </div>
</div>
</body>
</html>