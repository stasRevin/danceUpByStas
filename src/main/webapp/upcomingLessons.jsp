<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- https://mdbootstrap.com/content/bootstrap-table-pagination/ -->
<table class="display responsive nowrap" style="width:100%;">
    <thead>
    <tr>
        <c:choose>
            <c:when test="${role == 1}">
                <th>Student</th>
            </c:when>
            <c:otherwise>
                <th>Instructor</th>
            </c:otherwise>
        </c:choose>

        <th>Date</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Location Name</th>
        <th>Location Address</th>
        <th>Location City</th>
        <th>Location State</th>
        <th>Location Zip</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="userLesson" items="${userLessons}">
            <tr>
                <c:choose>
                    <c:when test="${role == 1}">
                        <td><a href="http://18.219.182.38:8080/danceup/instructorAccessStudentProfile?studentId=${userLesson.lesson.students[0].id}">${userLesson.lesson.students[0].firstName} ${userLesson.lesson.students[0].lastName}</a></td>
                    </c:when>
                    <c:when test="${role == 2}">
                        <td> <a href="http://18.219.182.38:8080/danceup/bookLesson?instructorId=${userLesson.lesson.instructors[0].id}">${userLesson.lesson.instructors[0].firstName} ${userLesson.lesson.instructors[0].lastName}</a></td>
                    </c:when>
                </c:choose>
                <td>${userLesson.lesson.date}</td><td>${userLesson.lesson.startTime}</td>
                <td>${userLesson.lesson.endTime}</td>
                <td>${userLesson.lesson.location.name}</td>
                <td>${userLesson.lesson.location.address1}</td>
                <td>${userLesson.lesson.location.city}</td>
                <td>${userLesson.lesson.location.state}</td>
                <td>${userLesson.lesson.location.postalCode}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>