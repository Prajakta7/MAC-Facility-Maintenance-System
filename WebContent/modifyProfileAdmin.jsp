<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
	<title>UTA MAC | Update Profile</title>
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
			<h1 align="center">Update Profile</h1>
		</div>
    </div>

<div class="container">
<form name="update_profile" action="UserController?action=modifyProfile" method="post">
<center>
<table>
<c:forEach items="${User}" var="User">
<tr><td><label for="utaid">UTA ID:</label></td><td><input type="text" name="utaid" value='${User.utaID}' disabled="disabled"></input></td><td></td></tr>
 

<tr><td><label for="fname">First Name</label></td><td><input type="text" name="fname" value='${User.firstName}'></input></td><br> <input name="fnameError" value='${uErrMsgs.fnameError}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" /></td></tr>


<tr><td><label for="lname">Last Name</label></td><td><input type="text" name="lname" value='${User.lastName}'></input></td><br> <input name="lnameError" value='${uErrMsgs.lnameError}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" /></td></tr>


<tr><td><label for="address">Address</label></td><td><input type="text" name="addr" value='${User.address}'></input></td></tr>
  <tr>
  <td><label for="address">Role</label></td>
  	<td >
			
			<select name="role">
			
			<option value='${User.roleType}'><c:out value="${User.roleType }" /></option>
			<option value='${User.roleType }'>------</option>
			<option value="Student">Student</option>
			<option value="Faculty">Faculty</option>
			<option value="Facility Manager">Facility Manager</option>
			<option value="Repairer">Repairer</option>
			</select>
			
			</td></tr>
<tr><td colspan="2"><input type="hidden" name="username" value="${User.userName}"/>
<input type="hidden" name="pwd" value='${User.password}'></input>
</td></tr></c:forEach>
<tr><td colspan="2"><center><input type="submit" name="btnUpdate" value="Confirm Update"></input></center></td></tr>

</table>
</center>
</form>

</div>
</body>
</html>