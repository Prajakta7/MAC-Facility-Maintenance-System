<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	
<!DOCTYPE html>
<html>
<head>
<title>UTA MAC |Add Facility</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="facilityManager.jsp">MAC Facility Management</a>
    		</div>
    		<ul class="nav navbar-nav">
          		<li><a href="facilityManager.jsp" >Home</a></li>     	
          		<!-- <li><a href="/project/FacilityController?action=listMar" >Unassigned Mars</a></li> -->
          		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Unassigned Mars
        		<span class="caret"></span></a>
        		<ul class="dropdown-menu">
          			<li><a href="/project/FacilityController?action=listMar">ALL Unassigned MAR's</a></li>
          			<li><a href="view_unmars_date.jsp">Search By Date</a></li>
      		   	</ul>
      			</li>
          		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Assigned Mars
        		<span class="caret"></span></a>
        		<ul class="dropdown-menu">
          			<li><a href="/project/FacilityController?action=ViewMar">ALL MAR's</a></li>
          			<li><a href="view_mars_date.jsp">Search By Date</a></li>
      		   	</ul>
      			</li>
          		<li><a href="/project/FacilityController?action=repairerSchedule">Repairers Schedule</a></li>
		  		<li><a href="/project/FacilityController?action=numberOfFacilities" >Number of facilities</a></li>
		  		<li><a href="/project/FacilityController?action=facilitynameDrop">Facility Details</a> </li> 
    	  		<li class="active"><a href="facility_add.jsp" >Add Facility</a></li>   
		  		<li><a href="/project/UserController?action=updateProfile">Update Profile</a></li>	
		  		<li><a href="/project/UserController?action=logout">Logout </a> </li> 
    		</ul>
  		</div>
	</nav>
<div class="container">
<div class="jumbotron">
			<h1 align="center">Add Facility</h1>
</div>
<center>
<input name="errMsg"   value='${errorMsgs.errorMsg}' type="text"  style ="background-color: white; color: red; border: none; width:100%;" disabled="disabled">

<form name="add_facility" action="FacilityController" method="post">

<table>
<tr><td>Facility Type :</td><td><input type="text" value='${facility.facilityType}' name="facility_type" ></input></td>
<td><input type="text" name="error_facility_type" value='${errorMsgs.facilityTypeError}' style ="background-color: white; color: red; border: none; width:1000px;"  disabled="disabled"></input></td>
</tr>
<tr><td>Facility Name :</td><td><input type="text" value='${facility.facilityName}' name="facility_name" ></input></td>
<td><input type="text" name="error_facility_type" value='${errorMsgs.facilityNameError}' style ="background-color: white; color: red; border: none; width:100%;"  disabled="disabled"></input></td>
</tr>
<tr><td>Venue :</td><td>
<select name="venue">
 <option value="Indoor"  >Indoor</option>
  <option value="Outdoor">Outdoor</option>
</select>
</td>
<td><input type="text" name="error_facility_type" value='' style ="background-color: white; color: red; border: none; width:100%;"  disabled="disabled" ></input></td>
</tr>
<tr><td>Interval :</td><td><select name="interval">
  <option value="30 min" selected>30 min</option>
  <option value="1 Hour"  >1 Hour</option>
  <option value="2 Hours">2 Hours</option>

</select></td></tr>
<tr><td>Duration :</td><td><select name="duration" >
  <option value="Same day" selected >Same Day</option>
  <option value="7-Day"  >7-Day</option>
<input type="hidden" name="action" value="facility_add"/></input>
</select></td></tr>
<tr><td colspan="2"><br></td></tr>
<tr><td colspan="2"><center><input type="submit" name="submit" value="Submit"></input></center></td></tr>
</table>
</form>

</center>
</div>
</body>
</html>