<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<title>UTA MAC |Repairer Schedule </title>
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
          		<li><a href="/project/FacilityController?action=listMar" >Unassigned Mars</a></li>
          		<li><a href="/project/FacilityController?action=ViewMar" >Assigned Mars</a></li>
          		<li class="active"><a href="/project/FacilityController?action=repairerSchedule">Repairers Schedule</a></li>
		  		<li><a href="/project/FacilityController?action=numberOfFacilities" >Number of facilities</a></li>
		  		<li><a href="/project/FacilityController?action=facilitynameDrop">Facility Details</a> </li> 
    	  		<li><a href="facility_add.jsp" >Add Facility</a></li>   
		  		<li><a href="/project/UserController?action=updateProfile">Update Profile</a></li>	
		  		<li><a href="/project/UserController?action=logout">Logout </a> </li> 
    		</ul>
  		</div>
	</nav>
<div class="container">
<div class="jumbotron">
			<h2 align="center">Repairer Schedule</h2>
</div>	
<center>
<table border="1">
<tr>
<th style="padding-right: 20px;"> Repairer Id </th>
<th style="padding-right: 20px; "> MAR Number </th>
<th style="padding-right: 35px; "> Facility Name </th>
<th style="padding-right: 20px; "> Date </th>
<th style="padding-right: 20px; "> Time Slot </th>
 
 <c:forEach items="${repairerSchedule}" var="item" varStatus="status">
			<tr>	
			<td style="padding-right: 20px; "><c:out value="${item.repairer_id}" /></td>
			<td style="padding-right: 25px; "><c:out value="${item.mar_id}" /></td>
			<td style="padding-right: 35px; "><c:out value="${item.facility_name}" /></td>
			<td style="padding-right: 20px; "><c:out value="${item.date}" /></td>
            <td style="padding-right: 20px; "><c:out value="${item.timeslot}" /></td>
			</tr>
		</c:forEach>
</tr>

</table>
</center>
</div>
</body>
</html>