<jsp:include page="contentType.jsp"/>
<html>
  <jsp:include page="head.jsp"/>
  <body>
      <div class="container">
          <div class="row">
              <jsp:include page="jumbotron.jsp"/>
              <jsp:include page="navbar.jsp"/>
              <form class="form-horizontal" id="multipleForm" action="/danceup/userSignUp" method="post" enctype="multipart/form-data">

                  <div class="form-group">
                      <label class="control-label col-sm-3">Sign up as</label>
                      <div class="col-sm-9">
                        <input type="radio" name="role" id="studentRole" value="student"> Student
                        &nbsp;&nbsp;
                        <input type="radio" name="role" id="instructorRole" value="instructor"> Dance Instructor
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-3">Username</label>
                      <div class="col-sm-6">
                        <input class="form-control" type="text" name="username">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-sm-3">First Name</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="firstName" />
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3">Last Name</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="lastName" />
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3">Email address</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="email" />
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3">Address 1</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="address1">
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3">Address 2</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="address2">
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3">City</label>
                      <div class="col-sm-6">
                          <input class="form-control" type="text" name="city">
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-3"> State</label>
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
                          <input class="form-control" type="text" name="zip">
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
                      <label class="control-label col-sm-3">Upload Profile Photo</label>
                      <div class="col-sm-6">
                          <input type="file" name="profilePhoto">
                      </div>
                  </div>

                  <div class="form-group">
                      <div class="col-sm-offset-3 col-sm-5">
                          <input class="btn btn-primary" type="submit" value="Submit" class="btn btn-default">
                          <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                      </div>
                  </div>

              </form>
              <jsp:include page="footer.jsp"/>
          </div>
      </div>
  </body>
</html>
<script type="text/javascript" charset="UTF-8">
    signUpEventsInit()
</script>