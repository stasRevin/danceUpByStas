<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- https://mdbootstrap.com/content/bootstrap-table-pagination/ -->
<table class="table dataTable table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
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
    <c:forEach var="userLesson" items="${userLessons}">
        <tr><td>${userLesson.lesson.date}</td><td>${userLesson.lesson.startTime}</td>
            <td>${userLesson.lesson.endTime}</td>
            <td>${userLesson.lesson.location.name}</td>
            <td>${userLesson.lesson.location.address1}</td>
            <td>${userLesson.lesson.location.city}</td>
            <td>${userLesson.lesson.location.state}</td>
            <td>${userLesson.lesson.location.postalCode}</td>
        </tr>
    </c:forEach>
</table>