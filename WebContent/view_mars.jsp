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
			<h1 align="center">List of MAR's</h1>
		</div>
       <table border="1" class="table"> 
			<tr> 
				
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Mar_Number</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Type</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Description</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Urgency</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Reported By</th> 
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Date</th>  
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Assigned To</th>   
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Select Mar</th> 
			</tr>

 		<c:forEach items="${mars}" var="item" varStatus="status">
			<tr class="myTableRow">
		 	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.mar_number}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facility_type}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facility_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.desc}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.reported_by}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.date}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.assigned_to}" /></td>
            <td> <a href="/project/FacilityController?action=assignMar&id=${item.mar_number}">View</a></td>
			</tr>
		</c:forEach>
 </table>

<input type="text" value='${msg.msg}' disabled style="border:none;width:1000px;font-size:20px; color:red;" />

</div>
</body>
</html>