<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<title>UTA MAC |Facility Detail </title>
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
		  		<li class="active"><a href="/project/FacilityController?action=facilitynameDrop">Facility Details</a> </li> 
    	  		<li><a href="facility_add.jsp" >Add Facility</a></li>   
		  		<li><a href="/project/UserController?action=updateProfile">Update Profile</a></li>	
		  		<li><a href="/project/UserController?action=logout">Logout </a> </li> 
    		</ul>
  		</div>
	</nav>

<div class="container">
<div class="jumbotron">
			<h2 align="center">Facility Details</h2>
</div>
<center>
<h2>Select from drop down to view details</h2>
<form action="FacilityController?action=facilityDetails" method="post" >
Facility Name:&nbsp;
<select name="facilityname">
    <c:forEach items="${nameDropdown}" var="name">
       <option value="${name.facilityName}" ><c:out value="${name.facilityName}"/></option>
        
    </c:forEach>
</select><br/>
<input type="submit">
</form>

</center>
<h4> Facility Details: </h4>
<textarea rows="7" cols="50">
<c:forEach items="${facilityDetails}" var="det">
       Facility_Type: <c:out value="${det.facilityType}" />
       interval: <c:out value="${det.interval}" />
       Duration: <c:out value="${det.duration}" />
       Venue: <c:out value="${det.venue}" />
</c:forEach>
</textarea>
</body>
</html>