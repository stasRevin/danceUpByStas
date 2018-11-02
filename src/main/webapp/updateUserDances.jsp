<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="contentType.jsp"/>
<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <c:choose>
            <c:when test="${role == 1}">
                <jsp:include page="instructorNavbar.jsp"/>
            </c:when>
            <c:when test="${role == 2}">
                <jsp:include page="studentNavbar.jsp"/>
            </c:when>
        </c:choose>

        <div class="col-sm-12">

            <form action="/danceup/insertUserDance" method="post">
                <div class="form-group">
                    <label for="formControlSelect1">Select a Dance</label>
                    <select class="form-control" name="danceName" id="formControlSelect1">
                        <c:forEach var="dance" items="${dances}">
                            <option>${dance.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">

                        <c:choose>
                            <c:when test="${role == 1}">
                            <label for="formControlSelect2"> Years of Experience</label>
                                <select class="form-control" name="experience" id="formControlSelect2">
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <label for="formControlSelect3">Learning Proficiency</label>
                                <select class="form-control" name="learningProficiency" id="formControlSelect3">
                                    <option>10</option>
                                    <option>20</option>
                                    <option>30</option>
                                    <option>40</option>
                                    <option>50</option>
                                    <option>60</option>
                                    <option>70</option>
                                    <option>80</option>
                                    <option>90</option>
                                    <option>100</option>
                                </select>
                            </c:otherwise>
                        </c:choose>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Add Dance</button>
            </form>
            <table class="table">
                <thead>
                    <tr><th>Dance Name</th><th>Experience (years)</th><th></th><th></th></tr>
                </thead>
                <tbody>
                    <c:forEach var="userDance" items="${userDances}">
                        <tr>
                            <td>${userDance.dance.name}</td>
                            <td>${userDance.yearsOfExperience}</td>
                            <td><span class="deleteDance" data-delete="${userDance.dance.name}"><img id="trashCan" src="images/trashCan.png" alt="trash can image"></span></td>
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