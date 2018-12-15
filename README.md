# Stanislav Revin - Individual Project
### Dance Up Web Application
### Problem Statement
In the era of a so called gig economy, a number of applications has emerged to accomodate users with finding workers/freelancers/assistants/helpers/drivers to perform a certain type of activities in exchange for a monetary renumeration on the per-job basis. The Dance Up Application will fit into that category as well. In the preparation for this application, I have explored websites like www.thumbtack.com and www.coachup.com. Both of the above sites offer a wide variety of freelance services and do not focus on any specific activity thus sacrificing some important details about the services offered expecting that users are already familiar with what they want and why. I believe that a more specilized website for finding not just anybody for anything but specifically a ballroom dance instructor for dance lessons in the user's area will provide a better quality of service to people who are interested in dancing by focusing all the content on dance-related topics. The solution will provide users with an ability to create a personalized profile and find a dance instuctor(s) in the area and select a location for lessons that's closer to them. The website will provide more details about dancing in general and attract a community of like-minded individuals who can meet together during the lessons. The dance instructors will benefit from the service by posting their profile that will highlight their expertise and attract users to book them for either a private or group session.

### Video Presentation
[Link to YouTube video](https://www.youtube.com/watch?v=JX6susee26k)

### AWS Link
[Link to the online instance](http://18.219.182.38:8080/danceup/)

### Project Technologies/Techniques 

* Security/Authentication
  * Instructor role: create a profile, store dances taught, view students' profiles, send notifications to students, update profile,
    create and delete schedule, add and delete teaching locations, store and display profile photo.
  * Student role: create a profile, store dances learned, find instructors nearby, send notifications to instructors, update profile, store and display profile photo
* Database (MySQL and Hibernate)
  * Store users and roles
  * Store application data
* ORM Framework
  * Hibernate
* Web Services or APIs
  * Zip Code Radius RESTful service (consumed)
  * Input validation RESTful service (created and consumed)
  * Instructor availability RESTful service (created and consumed)
* Data Validation
  * Custom build input validation service (works on the front and the back end)
* Logging
  * Configurable logging using Log4J. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting. 
* Hosting
  * AWS
* Independent Research Topic
  * Java 8 Lambdas
  * Java 7EE Filters and Listeners
  * Project Lombok
  * Enums
* Unit Testing
  * JUnit tests to achieve appropriate coverage to test all the business logic and calculations.


* [Screen Design](DesignDocuments/wireframes/images/)
* [Application Flow](DesignDocuments/applicationFlow.md)
* [Database Design](DesignDocuments/ERD.JPEG)
