# Application Flow

### Home

1. Information about the purpose of the application is displayed, providing links to either sign up or login (as instructor/Student/admin).

### Student Sign Up
1. Student is a type of User.
1. Student chooses sign up on the menu or follows a link to do so.
1. Student fills out the sign up form and submits it.
1. Request goes to the Student sign up servlet.
1. Servlet creates a Student object and then creates Student in the database.
1. Response is sent to Student to confirm that a new account was created (JSP).

### Instructor  Sign Up
1. Instructor is a type of User
1. Instructor selects to sign up as an instructor.
1. Instuctor fills out the sign up form and submits it.
1. Request goes to the instructor sign up servlet.
1. Servlet creates an instructor object and then creates instructor in the database.
1. Response is sent to instructor to confirm the a new account was created (JSP).

### Student Sign In
1. Student chooses sign in on the menu.
1. Student inputs username and password and submits it.
1. If Student is authenticated, the server will take care of granting required privileges to the Student to update Student data. Consider using JDBCRealm for authentication (Users, User_roles, roles tables).
1. If authentication fails, return an error message.

### Instructor Sign In
1. Instructor chooses to sign in on the menu.
1. Instructor inputs username and password and submits it.
1. If instructor is authenticate, the server will grant all required privileges to update instructor's data.

### Student Profile Page
1. Servet sends all required information to populate the page with the student's data:
	* Username
	* Name
	* Contact info
	* Dances in progress (learning)
	* Instructors used
	* Favorite dance music composition

1. Links to perform the following actions are provided as buttons:
	* Find an instructor
	* Book a lesson with an existing instructor
	* Update personal profile
	* Message an instructor? (if time permits)
	* View notifications

### Instructor Profile Page
1. Servlet sends all required  information to populate the page with the instructor's data:
	* Username
	* Name
	* Dances offered to teach
	* Number of active students
	* Current availability
	* Contact info
	* Locations at which lessons are offered
1. Links to perform the following actions are provided as buttons:
	* Message a student ?(if time permits)
	* Browse active students
	* Update personal profile
	* View notifications (lessons booked)

### Find Instructor Page
1. Servlet will send data to display a list of instructors in the students area or area provided in the search input field.
1. Servlet will send a create a list of Instructor Objects, query the database for the instructors in the specified area and create an instructor object for each instructor found, the results will be added to the list of instructors and sent with response.
1. Each instructor will have a link to his/her profile page (public view).
1. Pagination may be required, so the results don't come out too long.

### Find Student Page
1. Servlet will reply with data to display users located in the instructors area or area provided in the search input field.
1. Similar to Find Instructor functionality.
1. Each student will have a link to his/her profile page (public view).
1. Pagination may be required, so the results don't come out too long.

### Book a Lesson Page (with a specific instructor)
1. Servlet will respond with the form with the following input fields:
	* Start time (only instructor's available times will display)
	* End time
	* Number of people (up to 2 for a private session)
	* Location (Google maps)
	
1. Student will submit the form with the start and the end time.
1. Servlet will find the requested instructor and update his/her schedule with the requested times.

### About
1. Static html page


	