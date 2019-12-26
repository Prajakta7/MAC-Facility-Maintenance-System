<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<title>UTA MAC |Facility Number </title>
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
		  		<li class="active"><a href="/project/FacilityController?action=numberOfFacilities" >Number of facilities</a></li>
		  		<li><a href="/project/FacilityController?action=facilitynameDrop">Facility Details</a> </li> 
    	  		<li><a href="facility_add.jsp" >Add Facility</a></li>   
		  		<li><a href="/project/UserController?action=updateProfile">Update Profile</a></li>	
		  		<li><a href="/project/UserController?action=logout">Logout </a> </li> 
    		</ul>
  		</div>
	</nav>
<div class="container">
<div class="jumbotron">
			<h2 align="center">Number of Facilities Avaliable</h2>
</div>
<center>
<h2>Select from drop down to view details</h2>
<h4><span style="color:red;"><c:out value="${errorMsgs.assigned_DateError}" /></h4>
<form action="FacilityController?action=facilityNumbers" method="post" >
<table>
<tr>
<td>
Facility Type: &nbsp;
<select name="facilityType">

    <c:forEach items="${typeDropdown}" var="type">
       <option value="${type.facilityType}" ><c:out value="${type.facilityType}"/></option>
        
    </c:forEach>
</select><br/>



</td>

<td>
Date : &nbsp;
<input text="type" name="date" />
</td>
<td>Time slot &nbsp;: <select name="timeslot">
  <option value="00:00" >00:00</option>
  <option value="01:00" >01:00</option>
  <option value="02:00" >02:00</option>
  <option value="03:00" >03:00</option>
   <option value="04:00" >04:00</option>
  <option value="05:00" >O5:00</option>
  <option value="06:00" >06:00</option>
  <option value="07:00" >07:00</option>
  <option value="08:00" >08:00</option>
  <option value="09:00" >09:00</option>
   <option value="10:00" >10:00</option>
  <option value="11:00" >11:00</option>
  <option value="12:00" >12:00</option>
  <option value="13:00" >13:00</option>
  <option value="14:00" >14:00</option>
  <option value="15:00" >15:00</option>
   <option value="16:00" >16:00</option>
  <option value="17:00" >17:00</option>
  <option value="18:00" >18:00</option>
  <option value="19:00" >19:00</option>
  <option value="20:00" >20:00</option>
  <option value="21:00" >21:00</option>
   <option value="22:00" >22:00</option>
  <option value="23:00" >23:00</option>
</select></td>
</tr>
</table>
<input type="submit">
</form>
</center>
<br/><br/>
<h4><c:out value= "${facilityNumbers}" /></h4>
</div>
</body>
</html>