<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron">
		<h1 align="center">Welcome to UTA Maverick Facililty Maintenance System</h1>
	</div>
	<div align="center">
		<h1>Please Enter the Following Details:</h1>
	</div>

	<div class="container" align="center">
		<form name="registerForm" action="UserController" method="post">
			<div class="form-group">
				<label for="utaid">UTA ID:</label> <input type="text"
					class="form-control" name="utaid"
					placeholder="your 10 digit UTA ID"> <br> <input
					name="utaid" value='${errorMsgs.utaIDError}' type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="userName">User Name (Email)</label> <input type="text"
					class="form-control" name="userName" placeholder="example@xyz.com">
				<br> <input name="userNameError"
					value='${errorMsgs.userNameError}' type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" name="password" placeholder="Password">
				<br> <input name="passwordError"
					value='${errorMsgs.passwordError}' type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="cpassword">Confirm Password</label> <input
					type="password" class="form-control" name="cpassword"
					placeholder="Confirm Password"> <br> <input
					name="cpasswordError" value='${errorMsgs.cpasswordError}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<input type="hidden" name="action" value="Register">
			<div class="form-group">
				<label for="lname">Last Name</label> <input type="text"
					class="form-control" name="lname" placeholder="Last Name">
				<br> <input name="lnameError" value='${errorMsgs.lnameError}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="fname">First Name</label> <input type="text"
					class="form-control" name="fname" placeholder="First Name">
				<br> <input name="fnameError" value='${errorMsgs.fnameError}'
					type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="roleType">Role Type</label> <select class="form-control"
					name="roleType">
					<option>Student</option>
					<option>Faculty</option>
					<option>Facility Manager</option>
					<option value="repairer">Repairer</option>
					<option>Admin</option>
				</select>
			</div>
			<div class="form-group">
				<label for="mobile">Contact Number</label> <input type="text"
					class="form-control" name="mobile"
					placeholder="your 10 digit phone number"> <br> <input
					name="mobileError" value='${errorMsgs.mobileError}' type="text"
					style="background-color: white; color: red; border: none; width: 800px"
					disabled="disabled" maxlength="60" />
			</div>
			<div class="form-group">
				<label for="address">Address</label> <input type="text"
					class="form-control" name="address" placeholder="your address">
			</div>
			<div class="form-group">
				<label for="securityans">Security Question: Your favorite
					place? </label> <input type="text" class="form-control" name="securityans"
					placeholder="your favorite place">
			</div>

			<button type="submit" class="btn btn-primary" name="btnRegister">Register</button>
		</form>
		<div>
			<p align="center">Already User?</p>
			<form action="index.jsp">
				<button type="submit" class="btn btn-primary">Home</button>
			</form>
		</div>
	</div>
			
</body>
</html>