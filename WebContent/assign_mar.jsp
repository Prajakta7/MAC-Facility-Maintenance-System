<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UTA MAC |Assign MAR </title>
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
          		<li class="active"><a href="/project/FacilityController?action=listMar" >Unassigned Mars</a></li>
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
			<h1 align="center">MAR's Info</h1>
</div>
<form action="FacilityController" method="post">
<input type="text" name="error_facility_type" value='${errorMsgs.errorMsg}' style ="background-color: white; color: red; border: none; width:100%;"  disabled="disabled"></input>


<input type="text" name="error_facility_type" value='${errorMsgs.assigned_week}' style ="background-color: white; color: red; border: none; width:100%;"  disabled="disabled"></input>

<input type="text" name="error_facility_type" value='${errorMsgs.assigned_more}' style ="background-color: white; color: red; border: none; width:100%;"  disabled="disabled"></input>

 <table border="0" class="table"> 
    <tr>
    <td> Mar Number: </td>
    <td> <c:out value= "${mar.mar_number}" />  </td>
    <input type="hidden" name ="mar_number"  value='${mar.mar_number} ' > </input>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td> <select name="facility_name">
    <option value="${mar.facility_name}"><c:out value =  "${mar.facility_name}" /></option>
    <option value="${mar.facility_name}">-------</option>
     <c:forEach items="${facility}" var="item" varStatus="status">
    <option value="${item.facilityName}"  ><c:out value = "${item.facilityName}" /></option>
    </c:forEach>
    </select>
    </td>
    </tr>

     <tr>
    <td> Facility Type: </td>
    <td> <select name="facility_type">
    <option value="${mar.facility_type}"><c:out value = "${mar.facility_type}" /></option>
    <option value="${mar.facility_type}">-------</option>
     <c:forEach items="${facility}" var="item" varStatus="status">
    <option value="${item.facilityType}"  >${item.facilityType}</option>
    </c:forEach>
    </select>
    </td>
    </tr>

 <tr>
    <td> Urgency: </td>
    <td><select name="urgency">
    <option value="${mar.urgency}"><c:out value=" ${mar.urgency}" /></option>
    <option value="${mar.urgency}">-----</option>
  <option value="Unsuable" >Unusable</option>
  <option value="Major" >Major</option>
  <option value="Medium">Medium</option>
    <option value="Minor" >Minor</option>
 

</select></td>
    </tr>

  <tr>
    <td> Description: </td>
    <td> <input type="textArea" name="desc" value="${mar.desc}" /></input> </td>
    <td style="border:none;"><input type="text" name="error_facility_type" value='${errorMsgs.desc}' style ="background-color: white; color: red; border: none !important; width:100%;"  disabled="disabled"></input></td>
    
    </tr>

    <tr>
    <td> Reported By: </td>
    <td> <input type="text" name="reported_by" value="${mar.reported_by}" disabled/></input> </td>
    </tr>

    <tr>
    <td> Date: </td>
    <td> <input type="text" name="date" value="${mar.date}" disabled /> </td>
    
    </tr>

    <tr>
    <td> Assigned To: </td>
    
    <td> <select name="assigned_to"  > 
    <option value="${mar.assigned_to}"><c:out value = "${mar.assigned_to}" /></option>
    <option value="${mar.assigned_to}">------</option>
    <c:forEach items="${user}" var="item" varStatus="status">
    <option value="${item.utaID}" ><c:out value = "${item.lastName} + ': '+ ${item.utaID}" /> </option>
    </c:forEach>
     </select>
    </td>
    <td><span style="color:red;"><c:out value="${errorMsgs.assigned_to}" /></span></td>
    </tr>
    <tr>
    
    <tr>
    <td> Assigned Date: </td>
    <td> <input  name ="assigned_date" value="${mar.assigned_date}" /></input></td>
    <td style="border:none;"><input type="text" name="error_facility_type" value='${errorMsgs.assigned_DateError}' style ="background-color: white; color: red; border: none !important; width:100%;"  disabled="disabled"></input></td>
    
    </tr>
    <tr>
    
    <tr>
    <td> Estimate of Repair: </td>
    <td><select name="estimate">
    <option value="${mar.estimate_of_repair }"><c:out value=" ${mar.estimate_of_repair }" /></option>
    <option value="${mar.estimate_of_repair }">-----</option>
  <option value="30 min" >30 min</option>
  <option value="1 Hour" >One Hour</option>
  <option value="Multiple Hours">Multiple Hours</option>
    <option value="One day" >One Day</option>
  <option value="Multiple Days">Multiple Days</option>

</select></td><td><span style="color:red;">
<c:out value="${errorMsgs.estimate_of_repair}" /></span></td>
    </tr>
    
    <tr>
    <td colspan="2">
    <input type="hidden" name="action" value="assign_mar"></input>
    <input type="Submit"></input>
    </td></tr>
    </table>

</form>
</div>
</body>
</html>