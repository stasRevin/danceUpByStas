// For instructorViewProfile.jsp
$(document).ready(function () {

    $(".responsive").DataTable();
    $(".dataTables_length").addClass("bs-select");
    addDeletePhotoOnClickEvent();
    $( ".datepicker" ).datepicker({
        dateFormat: "yy/mm/dd"
    });

    $(".deleteDance").click(function() {

        $(this).closest("tr").remove();
        var thisClass = $(this);
        var danceName = thisClass.data("delete");
        console.log("dance name: " + danceName);
        $.get("http://localhost:8080/danceup/deleteUserDance?name=" + danceName, function () {});

    });

    $(".deleteLocation").click(function () {

        $(this).closest("tr").remove();
        var thisClass = $(this);
        var locationId = thisClass.data("delete");
        console.log("location id: " + locationId);
        $.get("http://localhost:8080/danceup/deleteInstructorLocation?id=" + locationId, function () {});

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

        $.get("http://localhost:8080/danceup/deleteInstructorSchedule?date=" + date + "&startTime=" + startTime + "&endTime=" + endTime, function () {});
    });



});

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
        url: "http://localhost:8080/danceup/deleteUserPhoto",
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

