<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
	<form name="forgotpswdForm" action="UserController"
		method="post">
		<div class="form-group">
			<label for="securityquestion">Security Question: Your
				favorite place? </label> <input type="text" class="form-control"
				name="securityquestion" placeholder="your favorite place"> <br>
		</div>
		<div class="form-group">
			<label for="utaid">UTA ID:</label> <input type="text"
				class="form-control" name="utaid" placeholder="your 10 digit UTA ID">

		</div>
		<div class="form-group">
			<label for="password">New Password</label> <input type="password"
				class="form-control" name="password" placeholder="Password">
			<br>

		</div>
		<input type="hidden" name="action" value="Reset">
		<div class="form-group">
			<label for="cpassword">Confirm Password</label> <input
				type="password" class="form-control" name="cpassword"
				placeholder="Confirm Password"> <br> <input
				name="resetPasswordError" value='${errorMsgs.resetPasswordError}'
				type="text"
				style="background-color: white; color: red; border: none; width: 800px"
				disabled="disabled" maxlength="60">
		</div>
		<div>
			<p align="left">Submit to reset your password</p>
			<button name="btnReset" type="submit" class="btn btn-primary">Reset</button>
	</form>
	</div>
</body>
</html>