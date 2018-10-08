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

$( function() {
    $( ".datepicker" ).datepicker();
} );


// For instructorViewProfile.jsp
$(document).ready(function () {

    $(".dataTable").DataTable();
    $(".dataTables_length").addClass("bs-select");

});