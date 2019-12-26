<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<title>UTA MAC | User Sucess</title>
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
			<h1 align="center">Successfully Done!</h1>
		</div>
    </div>
</body>
</html>