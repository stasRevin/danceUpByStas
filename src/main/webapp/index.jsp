<jsp:include page="contentType.jsp"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.12.1/TweenMax.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <title>Dance Up</title>
    <meta charset="utf-8">
</head>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="jumbotron.jsp"/>
        <jsp:include page="navbar.jsp"/>

        <div class="panel panel-primary">
            <div class="panel-heading">About</div>
            <div class="panel-body">
                In the era of a so called gig economy, a number of applications has emerged to accomodate users with finding workers/freelancers/assistants/helpers/drivers to perform a certain type of activities in exchange for a monetary renumeration on the per-job basis. The Dance Up Application will fit into that category as well. In the preparation for this application, I have explored websites like www.thumbtack.com and www.coachup.com. Both of the above sites offer a wide variety of freelance services and do not focus on any specific activity thus sacrificing some important details about the services offered expecting that users are already familiar with what they want and why. I believe that a more specilized website for finding not just anybody for anything but specifically a ballroom dance instructor for dance lessons in the area will provide a better quality of service to people who are interested in dancing by focusing all the content on dance-related topics. The solution will provide users with an ability to create a personalized profile and find a dance instuctor(s) in the area and select a location for lessons that's closer to them. The website will provide more details about dancing in general and attract a community of likely minded individuals who can meet together during the lessons. The dance instructors will benefit from the service by posting their profile that will highlight their expertise and attract users to book them for either a private or group session.
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>