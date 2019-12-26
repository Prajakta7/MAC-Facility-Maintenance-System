<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>UTA MAC | Admin</title>
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
      			<a class="navbar-brand" href="admin.jsp">MAC Facility Management</a>
    		</div>
    		<ul class="nav navbar-nav">
			 <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Modify Users
        		<span class="caret"></span></a>
        		<ul class="dropdown-menu">
          			<li><a href="/project/UserController?action=listRoles">All Users</a></li>
          			<li><a href="admin_search.jsp">Search By Name</a></li>
      		   	</ul>
      			</li>
           <li><a href="/project/UserController?action=updateProfilea">Update Profile</a></li> 
           <li><a href="/project/UserController?action=logout">Logout</a></li>  
    		</ul>
  		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1 align="center">Search by UserName or Role</h1>
		</div>
		<div class="row">
		<h6><c:out value="${em.userNameError}" /></h6>
		<div class="col-sm-6">
		<form action="UserController" method="get">
		<table class="table">
		<tr>
		<input type="hidden" name="action" value="searchByUsername"/>
		<td>Username</td><td><input type="text" name="username"/></td>
		</tr>
		<tr><td col-span="2"><input type="submit" value="Search By Username"/></td></tr>
		</table>
		
		</form>
		</div>
		<div class="col-sm-6">
		<form action="UserController" method="get">
		<table class="table">
		<tr>
		<input type="hidden" name="action" value="searchByRole"/>
		<td>Role Type</td><td>
		<select name="role">
		<option value="Student">Student</option>
			<option value="Faculty">Faculty</option>
			<option value="Facility Manager">Facility Manager</option>
			<option value="Repairer">Repairer</option>
			</select></td>
		</tr>
		<tr><td col-span="2"><input type="submit" value="Search By Role"/></td></tr>
		</table>
		
		</form>
		</div>
		</div>
    </div>
    
    
    
 
</body>
</html>