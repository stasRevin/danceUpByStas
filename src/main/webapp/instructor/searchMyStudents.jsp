<jsp:include page="contentType.jsp"/>
<html>

<body>
<div class="container">
    <div class="row align-items-center justify-content-center">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
        <div class="col-sm-12 pt-3">
            <form>
                <!-- Took search button from here https://bootsnipp.com/snippets/Q0Rvm-->
                <div name="searchBy">
                    <div class="form-group ">
                        <select class="form-control">
                            <option selected>Zip Code</option>
                            <option>City</option>
                            <option>State</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-3 short-page">
                    <button type="submit" class="btn btn-primary btn-block">Search</button>
                </div>
            </form>
        </div>
        <br/><br/>
        <jsp:include page="../footer.jsp"/>
    </div>
</div>
</body>
</html>
<jsp:include page="../head.jsp"/>