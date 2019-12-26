<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>UTA MAC | Reservation</title>
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
			<h1 align="center">Reservation Modify/Cancel</h1>
		</div>
		 <h6 style="color:red;"><c:out value="${resEr.errorMsg}"/></h6>
<form action="ReservationController" method="post">
<table>
  <tr>
   <td>
         <table border="1" class="table"> 
         
         <tr>
    <td> Reservation ID: </td>
    <td> <c:out value= "${res.reservation_id}" />  </td>
    <input type="hidden" name ="reservation_id"  value="${res.reservation_id}" > </input>
    </tr>
    <tr>
    <td> Mar Number: </td>
    <td> <c:out value= "${res.mar_id}" />  </td>
    <input type="hidden" name ="marid"  value="${res.mar_id}" > </input>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td><c:out value=" ${res.facility_name}" /><input type="hidden" name="fn" value="${res.facility_name}" /> </input>
    </td>
    </tr>

   



 

    <tr>
    <td> Date: </td>
    <td> <input type="hidden" name="adate" value="${res.date}" /> </input>${res.date}</td>
    </tr>

  
    
  
    
    <tr>
    <td> Time Slot: </td>
    <td><select name="timeslot">
      <option value="${res.timeslot}" ><c:out value="${res.timeslot}" /></option>
  <option value="${res.timeslot }" >------</option>
  
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
    <input type="hidden" name="repid" value="${res.repairer_id} "></input>
    <tr>
    <td colspan="2">
    <input type="hidden" name="action" value="modify"></input>
    <input type="Submit" value="Modify" ></input>  <a href="/project/ReservationController?action=cancel&id=${res.reservation_id}&date=${res.date}&fn=${res.facility_name}&timeslot=${res.timeslot}&marid=${res.mar_id}" ><input type="button" value="Cancel Reservation"/></a>
    <a href="/project/ReservationController?action=View/ModifyRepair" ><input type="button" value="Back"/></a></td></tr>
    </table>
</td>
</tr>
</table>
</form>
</body>
</html>