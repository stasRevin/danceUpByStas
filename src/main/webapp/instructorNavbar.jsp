<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/danceup">DanceUp</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/danceup/instructorViewProfile.jsp">View Profile</a></li>
                <li><a class="btn dropdown-toggle" data-toggle="dropdown" href="/danceup/userProfileUpdateForward">Update Profile<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/danceup/userProfileUpdateForward" role="button">Update My Profile</a></li>
                        <li><a href="/danceup/updateUserDances" role="button">Update My Dances</a></li>
                        <li><a href="/danceup/updateInstructorSchedule.jsp" role="button">Update My Schedule</a></li>
                        <li><a href="/danceup/updateInstructorLocations" role="button">Update My Teaching Locations</a></li>
                    </ul>
                </li>
                <li><a href="/danceup/notifications.jsp">Notifications<span class="badge badge-warning">4</span></a></li>
                <li><a href="/danceup/searchMyStudents.jsp">Students</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/danceup/userLogOut"><span class="glyphicon glyphicon-user"></span> Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>