<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>UTA MAC | Repairer Schedule</title>
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
      			<a class="navbar-brand" href="repairer.jsp">MAC Facility Management</a>
    		</div>
    		<ul class="nav navbar-nav">
				<li><a href="/project/ReservationController?action=repairerSchedule">Repairer Schedule </a></li>
				<li><a href="/project/ReservationController?action=View/ModifyRepair">View/Modify Repair</a></li>
				<li><a href="/project/UserController?action=updateProfiler">Update Profile </a></li>
				<li><a href="/project/UserController?action=logout">Logout </a></li>   
    		</ul>
  		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1 align="center">MAR's Assigned</h1>
		</div>
   
      
         
       <table border="1" class="table"> 
			<tr class="myTableRow"> 
				
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Mar_Number</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Type</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Description</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Urgency</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Repair Date</th>   
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Select Mar</th> 
			</tr>

 		<c:forEach items="${marsrep}" var="item" varStatus="status">
			<tr class="myTableRow">
		 	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.mar_number}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facility_type}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facility_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.desc}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.assigned_date}" /></td>
            <td> <a href="/project/ReservationController?action=reserveMar&id=${item.mar_number}&fn=${item.facility_name}&adate=${item.assigned_date}&ft=${item.facility_type}">Reserve</a></td>
			</tr>
		</c:forEach>
 </table>
</div>
</body>
</html>