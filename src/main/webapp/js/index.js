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
        var usernameInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "username/" + usernameInput, function (response) {

            performInputValidation(response, thisClass, "This username was either taken or invalid. Try again.");
        });

    });

    $("#firstName").change(function () {

        var thisClass = $(this);
        var firstNameInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "name/" + firstNameInput, function (response) {

            performInputValidation(response, thisClass, "The first name must consist only of alpha characters or alpha characters with an optional hyphen or a space between two words.");

        });

    });

    $("#lastName").change(function() {

        var thisClass = $(this);
        var lastNameInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "name/" + lastNameInput, function (response) {

            performInputValidation(response, thisClass, "The last name must consist only of alpha characters or alpha characters with an optional hyphen or a space between two words.");

        });

    });

    $("#address").change(function () {

        var thisClass = $(this);
        var addressInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "address/" + addressInput, function (response) {

            performInputValidation(response, thisClass, "The address must be of the format \"street number\" \"street name\" (street type (ST, AVE, BLVD) optionally.)");
        });
    });

    $("#city").change(function () {

        var thisClass = $(this);
        var cityInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "name/" + cityInput, function (response) {

            performInputValidation(response, thisClass, "The city name must consist only of alpha characters or alpha characters with an optional hyphen or a space between two words.");
        });
    });
    
    $("#zip").change(function () {

        var thisClass = $(this);
        var zipCodeInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "zip-code/" + zipCodeInput, function (response) {

            performInputValidation(response, thisClass, "The zip code must consist of exactly five digits.")
        });
    });

    $("#state").change(function () {

        var thisClass = $(this);
        var stateInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "state/" + stateInput, function (response) {

            performInputValidation(response, thisClass, "The state must be one of the provided below options.");
        });

    });

    $("#passwordConfirmation").change(function () {

        var thisClass = $(this);
        var passwordConfirmationInput = thisClass.val();
        var passwordInput = $("#password").val();

        if (passwordConfirmationInput !== passwordInput) {

            $(".error").remove();
            outputWarningMessage("Password and password confirmation must match.", thisClass, new Function());
        } else {

            $(".error").remove();
        }
    });

    $("#profilePhoto").change(function () {

        var photoFiles = document.getElementById("profilePhoto");
        var thisClass = $(this);

        validatePhotoFileSize(photoFiles, thisClass);

    });

    addInstructorsRateListener();

    $("#multipleForm").submit(function (event) {

        var username = $("#username");
        var firstName = $("#firstName");
        var lastName = $("#lastName");
        var address = $("#address");
        var city = $("#city");
        var state = $("#state");
        var zip = $("#zip");
        var rate = $("#instructorsRate");
        var password = $("#password");
        var passwordConfirmation = $("#passwordConfirmation");
        var photoFiles = document.getElementById("profilePhoto");
        var thisClass = $(this);

        var input = [username, firstName, lastName, address, city, state, zip, password, passwordConfirmation, rate];

        validateFormBeforeSubmission(input, event, thisClass);

        if (!validatePhotoFileSize(photoFiles, thisClass)) {

            event.preventDefault();
        }

    });


    $("#multipleFormUpdate").submit(function (event) {

        var username = $("#username");
        var firstName = $("#firstName");
        var lastName = $("#lastName");
        var address = $("#address");
        var city = $("#city");
        var state = $("#state");
        var zip = $("#zip");
        var rate = $("#instructorsRate");
        var photoFiles = document.getElementById("profilePhoto");
        var thisClass = $(this);

        var input = [username, firstName, lastName, address, city, state, zip, rate];

        validateFormBeforeSubmission(input, event, thisClass);

        if (!validatePhotoFileSize(photoFiles, thisClass)) {

            event.preventDefault();
        }

    });


    $(".deleteDance").click(function() {

        $(this).closest("tr").remove();
        var thisClass = $(this);
        var danceName = thisClass.data("delete");
        var danceSelection = $("#formControlSelect1");
        console.log("dance name: " + danceName);
        $.get(getServerHomeAddress() + "deleteUserDance?name=" + danceName, function () {
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
        $.get(getServerHomeAddress() + "deleteInstructorLocation?id=" + locationId, function () {
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

        $.get(getServerHomeAddress() + "deleteInstructorSchedule?date=" + date + "&startTime=" + startTime
                + "&endTime=" + endTime, function () {});
    });

    addInstructorAvailabilityEventHandler();


});

function getServerHomeAddress() {

    return "http://18.219.182.38:8080/danceup/";
    //return "http://localhost:8080/danceup/";
}

function getInputValidationAddress() {

    return getServerHomeAddress() + "input-validation/input-validator/";
}

function validateFormBeforeSubmission(input, event, thisClass) {

    removeErrors();

    for (var i = 0; i < input.length; i += 1) {

        var inputValue = input[i].val().trim();
        console.log("input value: " + inputValue);

        if (inputValue === "undefined" || inputValue === "" || inputValue === null) {

            outputWarningMessage("This field cannot be empty.", input[i], new Function());
            event.preventDefault();
        }
    }

}

function validatePhotoFileSize(photoFiles, thisClass) {

    var filesLength = photoFiles.files.length;

    if (filesLength > 0) {

        for (var i = 0; i <= filesLength - 1; i += 1) {

            var fileSize = photoFiles.files.item(i).size;
            if (fileSize > 140000) {

                outputWarningMessage("Your photo file is too large.", thisClass, new Function());
                return false;
            } else {

                $(".error").remove();
                return true;
            }
        }
    } else {

        return true;
    }
}

function performInputValidation(response, thisClass, errorMessage) {

    removeErrors();

    if (response === "false") {

        outputWarningMessage(errorMessage, thisClass, removeErrors);
    } else {

        removeErrors();
    }
}

function outputWarningMessage(errorMessage, thisClass, cleanUp) {

    cleanUp();
    var span = $("<span />").addClass("error").html(errorMessage);
    span.insertBefore(thisClass);
}

function removeErrors() {

    $(".error").remove();
}

function addInstructorAvailabilityEventHandler() {

    var lessonDateInput = $("#lessonDate");

    lessonDateInput.change(function () {

        var lessonDate = lessonDateInput.val().replace("/", "-").replace("/", "-");
        var instructorId = lessonDateInput.attr("data-instructorId");
        console.log(lessonDateInput.val());
        console.log("lessonDate" + lessonDate);
        console.log(instructorId);
        var hour = "";

        $.get(getServerHomeAddress() + "schedule-services/instructor-availabilities/"
            + lessonDate + "/" + instructorId, function(data) {

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

                }

                var select = $("#availableTime");
                select.html(html);

            });
    });
}

function signUpEventsInit() {

    var studentRole = document.getElementById("studentRole");
    var instructorRole = document.getElementById("instructorRole");

    if (window.addEventListener) {

        studentRole.addEventListener("click", removeInstructorsDetails, false);
        instructorRole.addEventListener("click", addInstructorsDetails, false);
    } else {

        studentRole.attachEvent("onclick", removeInstructorsDetails);
        instructorRole.attacheEvent("onclick", addInstructorsDetails(inputValidationAddress));
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

        addInstructorsRateListener();

    }

}

function addInstructorsRateListener() {

    $("#instructorsRate").change(function () {

        var thisClass = $(this);
        var rateInput = thisClass.val().trim();

        $.get(getInputValidationAddress() + "rate/" + rateInput, function (response) {

            performInputValidation(response, thisClass, "The rate must be in the format with the decimal point like \"00.00\".");
        });

    });
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
        url: getServerHomeAddress() + "deleteUserPhoto",
        success: function () {

            removePhotoFromPage();

            $.get(getServerHomeAddress() + "userViewProfileForward", function () {
                
            });
        }

    });
}


function removePhotoFromPage() {

    var sourcePath = $("#userPhoto").attr("src");
    $("#userPhoto").attr("src", "images/nopic.jpg");
}

function addDeletePhotoOnClickEvent() {

    $("#deletePhotoButton").click(function () {

        console.log("Adding delete event.");
        deleteUserPhoto();
    });
}

