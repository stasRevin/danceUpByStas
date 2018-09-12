# Stanislav Revin Individual Project
### Dance Up Web Application
### Problem Statement
In the era of a so called gig economy, a number of applications has emerged to accomodate users with finding workers/freelancers/assistants/helpers/drivers to perform a certain type of activities in exchange for a monetary renumeration on the per-job basis. The Dance Up Application will fit into that category as well. In the preparation for this application, I have explored websites like www.thumbtack.com and www.coachup.com. Both of the above sites offer a wide variety of freelance services and do not focus on any specific activity thus sacrificing some important details about the services offered expecting that users are already familiar with what they want and why. I believe that a more specilized website for finding not just anybody for anything but specifically a ballroom dance instructor for dance lessons in the area will provide a better quality of service to people who are interested in dancing by focusing all the content on dance-related topics. The solution will provide users with an ability to create a personalized profile and find a dance instuctor(s) in the area and select a location for lessons that's closer to them. The website will provide more details about dancing in general and attract a community of likely minded individuals who can meet together during the lessons. The dance instructors will benefit from the service by posting their profile that will highlight their expertise and attract users to book them for either a private or group session.

### Project Technologies/Techniques 

* Security/Authentication
  * Admin role: create/read/update/delete (crud) of all data
  * User role: create a profile, store dances learned, favorite dance music.
  * All: can browse website to read about dancing, instructor's tips, look for instructor (cannot book without a valid account)
* Database (MySQL and Hibernate)
  * Store users and roles
  * Store all data for the trails and reports
* ORM Framework
  * Hibernate
* Web Services or APIs
  * Google Maps for dance lesson location
* Data Validation
  * jQuery validator
* Logging
  * Configurable logging using Log4J. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting. 
* Hosting
  * AWS
* Independent Research Topic
  * Java 8 Lambdas
* Unit Testing
  * JUnit tests to achieve 80% code coverage

### Design
*
*
*

### [Project Plan](ProjectPlan.md)
* [Screen Design](DesignDocuments/Screens.md)
* [Application Flow](DesignDocuments/applicationFlow.md)
* [Database Design](DesignDocuments/databaseDiagram.png)

### [Development Journal](Journal.md)
