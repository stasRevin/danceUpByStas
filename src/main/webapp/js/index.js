// For instructorViewProfile.jsp
$(document).ready(function () {

    window.onpageshow = function (event) {

        if (event.persisted) {

            window.location.reload();
        }
    };

    $(".responsive").DataTable();
    $(".dataTables_length").addClass("bs-select");
    addDeletePhotoOnClickEvent();
    $( ".datepicker" ).datepicker({
        dateFormat: "yy/mm/dd"
    });

    $("#username").change(function () {

        var thisClass = $(this);
        var usernameInput = thisClass.val();
        console.log("username input: " + usernameInput);

        $.get("http://localhost:8080/danceup/inputValidationServices/inputValidator/validateUsername/" + usernameInput, function (response) {

            $(".error").remove();
            console.log("response: " + response);
            if (response === "false") {
                $(".error").remove();
                console.log("response inside the if statement: " + response);
                var span = $("<span />").addClass("error").html("This username was either taken or invalid. Try again.");
                span.insertBefore(thisClass);
            }
        });

    });

    $(".deleteDance").click(function() {

        $(this).closest("tr").remove();
        var thisClass = $(this);
        var danceName = thisClass.data("delete");
        var danceSelection = $("#formControlSelect1");
        console.log("dance name: " + danceName);
        $.get("http://18.219.182.38:8080/danceup/deleteUserDance?name=" + danceName, function () {
            danceSelection.append("<option>" + danceName + "</option>");
        })

    });

    $(".deleteLocation").click(function () {

        var row = $(this).closest("tr");
        var columns = row.find("td");
        var locationDeleted = "";

        jQuery.each(columns, function(index, item) {

            locationDeleted += item.innerHTML + " ";
            console.log("locationDeleted: " + locationDeleted)

        });

        $(this).closest("tr").remove();
        var thisClass = $(this);
        var locationId = thisClass.data("delete");
        var locationSelection = $("#locationSelection");
        console.log("location id: " + locationId);
        $.get("http://18.219.182.38:8080/danceup/deleteInstructorLocation?id=" + locationId, function () {
            locationSelection.append("<option value='" + locationId + "'>" + locationDeleted + "</option>");

            console.log("<option value='\" + locationId + \"'>\" + locationDeleted + \"</option>");
        });

    });

    //https://datatables.net/examples/api/select_single_row.html
    var table = $("#availabilityTable").DataTable();

    $("#availabilityTable tbody").on("click", "tr", function () {

        if ($(this).hasClass("selected")) {

            $(this).removeClass("selected");

        } else {

            table.$("tr.selected").removeClass("selected");
            $(this).addClass("selected");
        }
    });

    $("#deleteAvailability").click(function () {

        var selectedRow = $(".selected");

        table.row(".selected").remove().draw(false);

        $.each(selectedRow, function (key, value) {

            console.log(key + ": " + value);
        });

        var date = selectedRow.attr("data-date");
        var startTime = selectedRow.attr("data-start");
        var endTime = selectedRow.attr("data-end");

        $.get("http://18.219.182.38:8080/danceup/deleteInstructorSchedule?date=" + date + "&startTime=" + startTime
                + "&endTime=" + endTime, function () {});
    });

    addInstructorAvailabilityEventHandler();


});

function addInstructorAvailabilityEventHandler() {

    var lessonDateInput = $("#lessonDate");

    lessonDateInput.change(function () {

        var lessonDate = lessonDateInput.val().replace("/", "-").replace("/", "-");
        var instructorId = lessonDateInput.attr("data-instructorId");
        console.log(lessonDateInput.val());
        console.log("lessonDate" + lessonDate);
        console.log(instructorId);
        var hour = "";

        $.get("http://18.219.182.38:8080/danceup/scheduleServices/instructorDayAvailability/"
            + lessonDate + "/" + instructorId, function(data) {

            console.log("response: " + data);
        })

            .done(function (data) {

                var schedules = [];
                var html = "";

                for (index in data) {

                    hour = data[index]["startTime"].hour.toString();
                    hour = hour.length < 2 ? "0" + hour : hour;

                    console.log("hour len: " + hour.length);
                    console.log("new hour: " + hour);

                    html += "<option>" +  hour + ":00</option>";

                    console.log(data[index]["startTime"].hour);
                }

                var select = $("#availableTime");
                select.html(html);

            });
    });
}

function signUpEventsInit() {

    var studentRole = document.getElementById("studentRole");
    var instructorRole = document.getElementById("instructorRole");

    console.log("in signUpEventsInit function");
    if (window.addEventListener) {

        console.log("adding listener");
        studentRole.addEventListener("click", removeInstructorsDetails, false);
        instructorRole.addEventListener("click", addInstructorsDetails, false);
    } else {

        studentRole.attachEvent("onclick", removeInstructorsDetails);
        instructorRole.attacheEvent("onclick", addInstructorsDetails);
    }

}

function addInstructorsDetails() {

    var formGroupDiv = document.getElementById("formGroupDiv");

    if (formGroupDiv == null) {
        var form = document.getElementById("multipleForm");
        var label = document.createElement("label");
        formGroupDiv = document.createElement("div");
        var inputDiv = document.createElement("div");
        var input = document.createElement("input");
        var labelValue = document.createTextNode("Rate per lesson ($)");

        formGroupDiv.className = "form-group";
        formGroupDiv.id = "formGroupDiv";
        label.className = "control-label col-sm-3";
        label.appendChild(labelValue);
        inputDiv.className = "col-sm-6";
        input.className = "form-control";
        input.name = "ratePerLesson";
        input.type = "text";
        input.id = "instructorsRate";

        inputDiv.appendChild(input);
        formGroupDiv.appendChild(label);
        formGroupDiv.appendChild(inputDiv);
        form.insertBefore(formGroupDiv, document.getElementById("passwordDiv"));
    }

}

function removeInstructorsDetails() {

    var formGroupDiv = document.getElementById("formGroupDiv");

    if (!(formGroupDiv == null)) {
        var form = document.getElementById("multipleForm");
        form.removeChild(formGroupDiv);
    }
}



//Remove user photo from the update page
function deleteUserPhoto() {


    $.get({
        url: "http://18.219.182.38:8080/danceup/deleteUserPhoto",
        success: function () {

            console.log("removing photo.");
            removePhotoFromPage();
        }

    });

}

function removePhotoFromPage() {

    var sourcePath = $("#userPhoto").attr("src");
    $("#userPhoto").attr("src", "");
}

function addDeletePhotoOnClickEvent() {

    $("#deletePhotoButton").click(function () {

        console.log("Adding delete event.");
        deleteUserPhoto();
    });
}

