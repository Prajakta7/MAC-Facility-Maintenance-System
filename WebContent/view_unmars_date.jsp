<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UTA MAC |Assigned Mars </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<body>
   
	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="facilityManager.jsp">MAC Facility Management</a>
    		</div>
    		<ul class="nav navbar-nav">
          		<li class="active"><a href="facilityManager.jsp" >Home</a></li>     	
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
    	  		<li><a href="facility_add.jsp" >Add Facility</a></li>   
		  		<li><a href="/project/UserController?action=updateProfile">Update Profile</a></li>	
		  		<li><a href="/project/UserController?action=logout">Logout </a> </li> 
    		</ul>
  		</div>
	</nav>
      
         <div class="container">
         <div class="jumbotron">
			<h1 align="center">Enter date to get List of UnAssigned MAR's</h1>
		</div>
		<form  action="FacilityController" method="get">
		<center>
		<table style="align:center" class="table">
	<input type="hidden" name="action" value="viewbydateun"/>
		<tr>
		<td>Date</td>
		<td><input type="text" name="date" value='${mar.assigned_date}' />
		</td>
		<td><span style="color:red;"><c:out value="${errorMsgs.assigned_DateError}" /></span>
		</td>
		</tr>
		<tr>
		<td colspan="3">
		<input type="submit" value="search"/>
		</td>
		</tr>
		
		</table>
		</center>
		</form>
      


</div>
</body>
</html>