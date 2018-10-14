<html>
<jsp:include page="head.jsp"/>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="../general/jumbotron.jsp"/>
        <jsp:include page="instructorNavbar.jsp"/>
        <div style="float:right">
            <a class="btn btn-primary" href="instructorProfileUpdate.jsp" role="button">Update My Profile</a>
        </div>
        <br/><br/>
        <div style="float:right">
            <a class="btn btn-primary" href="/updateInstructorDances.jsp" role="button">Update My Dances</a>
        </div>
        <div style="float:left">
            <a class="btn btn-primary" href="/updateInstructorDances.jsp" role="button" style="background-color: red">Delete My Schedule</a>
        </div>
        <br/> <br/>
        <div class="col-sm-12">
            <form class="form-horizontal" id="multipleForm" action="#" method="post">
                <h4>Select Schedule Date Range</h4>
                <div class="form-group">
                    <label class="control-label col-sm-3"> Start Date</label>
                    <div class="col-sm-9">
                        <input type="text" class="datepicker">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Date</label>
                    <div class="col-sm-9">
                        <input type="text" class="datepicker">
                    </div>
                </div>
                <h4>Select General Availability</h4>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Monday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="mondayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="mondayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Tuesday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="tuesdayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="tuesdayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Wednesday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="wednesdayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="wednesdayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Thursday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="thursdayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="thursdayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Friday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="fridayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="fridayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Saturday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="saturdayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="saturdayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div style="text-align: center">
                        <label class="col-sm-12"> Sunday</label>
                    </div>
                    <label class="control-label col-sm-3"> Start Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="sundayStartTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3"> End Time</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="sundayEndTime">
                            <option value="off">Off</option>
                            <option value="8:00">8AM</option>
                            <option value="9:00">9AM</option>
                            <option value="10:00">10AM</option>
                            <option value="11:00">11AM</option>
                            <option value="12:00">12PM</option>
                            <option value="13:00">1PM</option>
                            <option value="14:00">2PM</option>
                            <option value="15:00">3PM</option>
                            <option value="16:00">4PM</option>
                            <option value="17:00">5PM</option>
                            <option value="18:00">6PM</option>
                            <option value="19:00">7PM</option>
                            <option value="20:00">8PM</option>
                            <option value="21:00">9PM</option>
                            <option value="22:00">10PM</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-6">
                        <button class="btn btn-primary" type="submit" class="btn btn-default">Update</button>
                        <button class="btn btn-primary" type="reset" class="btn btn-default">Clear</button>
                    </div>
                </div>
            </form>
            <jsp:include page="footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>