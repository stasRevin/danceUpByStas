<jsp:include page="../contentType.jsp"/>
<html>
<jsp:include page="../head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="/updateInstructorDances.jsp" role="button">Update My Dances</a>
        </div>
        <br/><br/>
        <div style="float:right">
            <a class="btn btn-primary" href="/updateInstructorSchedule.jsp" role="button">Update My Schedule</a>
        </div>
        <br/><br/>
        <br/><br/>
        <form class="form-horizontal" id="multipleForm" action="/danceup/updateUserProfile" method="post" enctype="multipart/form-data">
            <div class="form-group" style="margin-left: 24%">
                <div class="col-sm-3">
                    <img id="userPhoto" src="images/userPhotos/${user.photoName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Photo</label>
                <div class="col-sm-6">
                    <input type="file" name="profilePhoto"><br/>
                    <input type="button" value="Delete Photo" id="deletePhotoButton">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Username</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="username" value="${user.username}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">First Name</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="firstName" value="${user.firstName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">Last Name</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="lastName" value="${user.lastName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">Address 1</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="address1" value="${user.addressOne}">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">Address 2</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="address2" value="${user.addressTwo}">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">City</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="city" value="${user.city}">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-3"> State</label>
                Current State: ${user.state}
                <div class="col-sm-6">
                    <select class="form-control" name="state">
                        <option value="">None selected</option>
                        <option value="AK">Alaska</option>
                        <option value="AL">Alabama</option>
                        <option value="AR">Arkansas</option>
                        <option value="AZ">Arizona</option>
                        <option value="CA">California</option>
                        <option value="CO">Colorado</option>
                        <option value="CT">Connecticut</option>
                        <option value="DC">District of Columbia</option>
                        <option value="DE">Delaware</option>
                        <option value="FL">Florida</option>
                        <option value="GA">Georgia</option>
                        <option value="HI">Hawaii</option>
                        <option value="IA">Iowa</option>
                        <option value="ID">Idaho</option>
                        <option value="IL">Illinois</option>
                        <option value="IN">Indiana</option>
                        <option value="KS">Kansas</option>
                        <option value="KY">Kentucky</option>
                        <option value="LA">Louisiana</option>
                        <option value="MA">Massachusetts</option>
                        <option value="MD">Maryland</option>
                        <option value="ME">Maine</option>
                        <option value="MI">Michigan</option>
                        <option value="MN">Minnesota</option>
                        <option value="MO">Missouri</option>
                        <option value="MS">Mississippi</option>
                        <option value="MT">Montana</option>
                        <option value="NC">North Carolina</option>
                        <option value="ND">North Dakota</option>
                        <option value="NE">Nebraska</option>
                        <option value="NH">New Hampshire</option>
                        <option value="NJ">New Jersey</option>
                        <option value="NM">New Mexico</option>
                        <option value="NV">Nevada</option>
                        <option value="NY">New York</option>
                        <option value="OH">Ohio</option>
                        <option value="OK">Oklahoma</option>
                        <option value="OR">Oregon</option>
                        <option value="PA">Pennsylvania</option>
                        <option value="PR">Puerto Rico</option>
                        <option value="RI">Rhode Island</option>
                        <option value="SC">South Carolina</option>
                        <option value="SD">South Dakota</option>
                        <option value="TN">Tennessee</option>
                        <option value="TX">Texas</option>
                        <option value="UT">Utah</option>
                        <option value="VA">Virginia</option>
                        <option value="VT">Vermont</option>
                        <option value="WA">Washington</option>
                        <option value="WI">Wisconsin</option>
                        <option value="WV">West Virginia</option>
                        <option value="WY">Wyoming</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Zip Code</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="zip" value="${user.postalCode}">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">Rate per lesson ($)</label>
                <div class="col-sm-6">
                    <input class="form-control" type="text" name="ratePerLesson" value="${user.payRate}">
                </div>
            </div>

            <div class="form-group" id="passwordDiv">
                <label class="control-label col-sm-3">Password</label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" name="password">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">Confirm Password</label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" name="passwordConfirmation">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="btn btn-primary" type="submit" class="btn btn-default">Update</button>
                    <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                </div>
            </div>

        </form>
        <jsp:include page="../footer.jsp"/>
    </div>
</div>
</body>
</html>