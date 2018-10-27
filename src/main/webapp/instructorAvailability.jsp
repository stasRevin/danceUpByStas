<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="display responsive nowrap" style="width:100%;">
    <thead>
    <tr><th>Date</th><th>Start Time</th><th>End Time</th></tr>
    </thead>
    <tbody>
    <c:forEach var="schedule" items="${schedules}">
        <tr><td>${schedule.date}</td><td>${schedule.startTime}</td><td>${schedule.endTime}</td></tr>
    </c:forEach>
    </tbody>
</table>