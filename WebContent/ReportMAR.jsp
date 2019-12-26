<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<title>UTA MAC | User</title>
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
      			<a class="navbar-brand" href="user.jsp">MAC Facility Management</a>
    		</div>
    		<ul class="nav navbar-nav">
    			<li><a href="user.jsp">Home </a></li>
    			<li><a href="/project/MARController?action=reportMar">Report</a></li>
          		<li><a href="/project/UserController?action=updateProfileu">Update Profile</a></li>
				<li><a href="/project/UserController?action=logout">Logout </a></li>
    		</ul>
  		</div>
	</nav>
	
	<div class="container">
		<div class="jumbotron">
			<h1 align="center">Report MAR</h1>
		</div>
    
<form name="add_MAR" action="MARController?action=addMar" method="post">
<center>
<table>

<tr><td>Facility Type:</td><td>
<select name="facilitytype">
    <c:forEach items="${listFacility}" var="facility">
        <option value="${facility.facilityType}"><c:out value="${facility.facilityType}" /></option>
        
    </c:forEach>
</select><br/>
</td><td></td></tr>

<tr><td>Facility Name:</td><td>
<select name="facilityname">
    <c:forEach items="${listFacility}" var="facility">
        <option value="${facility.facilityName}"><c:out value="${facility.facilityName}"/></option>
        
    </c:forEach>
</select><br/>
</td><td></td></tr>

<tr><td>Urgency :</td><td><select 
					name="urgency">
					<option value="unusable">Unusable</option>
					<option value="major">Major</option>
					<option value="medium ">Medium</option>
					<option value="minor">Minor</option>
				</select></td><td></td></tr>

				
		
<tr><td>Description :</td><td><input type="text" name="desc" value='${MAR.desc}'></input></td><br> <td><input name="descError" value='${errorMsgs.desc}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" /></td></tr>

<tr><td colspan="2"><br></td></tr>





<tr><td colspan="2"><center><input type="submit" name="submit" value="Submit"></input></center></td></tr>

</table>
</center>
</form>
</div>

</body>
</html>