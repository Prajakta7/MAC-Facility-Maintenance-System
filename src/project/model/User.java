package project.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.data.UserDAO;

public class User {
	private String userName;
	private String password;
	private String roleType;
	private static final long serialVersionUID = 1L;
	private String cpassword;
	private String firstName;
	private String lastName;
	private String utaID;
	private String mobile;
	private String address;
	private String securityans;
	private String utaid;


	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleType() {
		return this.roleType ;
	}
	/*public void setId(String utaid) {
		this.utaid = utaid;
	}*/
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public String getRoleType(User login) {
		String un = login.getUserName();
		String roleType = UserDAO.getRoleTypeFromDb(un);
		return roleType;
	}*/
	
	/*public String getID(User login)
	{
		String un = login.getUserName();
		String utaid = UserDAO.getIDFromDb(un);
		return utaid;
	}*/
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUtaID() {
		return utaID;
	}

	public void setUtaID(String utaID) {
		this.utaID = utaID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecurityans() {
		return securityans;
	}

	public void setSecurityans(String securityans) {
		this.securityans = securityans;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public void validateUserName(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String un = register.getUserName();
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(un);
		if (un.equals("")) {
			res = "The User Name Field cannot be empty";
		} else if (un.length() < 6) {
			res = "The User Name must be at least 6 characters";
		} else if (un.length() > 30) {
			res = "The User Name cannot exceed 30 characters";
		} else if (!matcher.matches()) {
			res = "Invalid User Name";
		} else if (UserDAO.uniqueUserName(un)) {
			res = "The User Name already Exists";
		}
		errorMsgs.setUserNameError(res);
	}
	
	public void validateRoleType(User register,UserErrorMsgs errorMsgs)
	{
		String res = "";
		if(getRoleType().equalsIgnoreCase(""))
		{
			res="No value in roletype";
		}
		errorMsgs.setRoleTypeError(res);
	}
	
	/*public void validateID(User register,UserErrorMsgs errorMsgs)
	{
		String res = "";
		if(getUtaID().equalsIgnoreCase(""))
		{
			res="No value in ID";
		}
		errorMsgs.setidError(res);
	}*/


	public void validatePassword(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String pass = register.getPassword();
		boolean sf = false;
		final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^_&+=])(?=\\S+$).{6,18}";
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);
		for (int i = 0; i < pass.length(); i++) {
			if (pass.charAt(i) == ' ') {
				sf = true;
				break;
			}
		}
		if (pass.equals("")) {
			res = "Password Field cannot be Empty";
		} else if (pass.length() < 6) {
			res = "Password length must be at least 6 characters";
		} else if (pass.length() > 12) {
			res = "Password length cannot exceed 12 characters";
		} else if (sf) {
			res = "password cannot contain blank space character";
		} else if (!matcher.matches()) {
			res = "Invalid password it must contain at least 1 digit 1 lower case 1 upper case 1 special character";
		}
		errorMsgs.setPasswordError(res);
	}

	public void validateCpassword(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String pass = register.getPassword();
		String cpass = register.getCpassword();
		if (cpass.equals("")) {
			res = "confirm password field cannot be empty";
		} else if (!cpass.equals(pass)) {
			res = "passwords do not match";
		}
		errorMsgs.setCpasswordError(res);
	}

	public void validateFirstName(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String fname = register.getFirstName();
		boolean n = true;
		char nameArr[] = fname.toCharArray();
		for (char c : nameArr) {
			if (!(Character.isLetter(c) || Character.isSpace(c))) {
				n = false;
				break;
			}
		}
		if (fname.equals("")) {
			res = "First Name field cannot be empty";
		} else if (fname.length() > 50) {
			res = "FirstName Field cannot exceed 50 characters";
		} else if (!Character.isLetter(fname.charAt(0))) {
			res = "First character must be a letter";
		} else if (!n) {
			res = "FirstName cannot have digits or special characters";
		}
		errorMsgs.setFnameError(res);
	}

	public void validateLastName(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String lname = register.getLastName();
		boolean n = true;
		char nameArr[] = lname.toCharArray();
		for (char c : nameArr) {
			if (!(Character.isLetter(c) || Character.isSpace(c))) {
				n = false;
				break;
			}
		}
		if (lname.equals("")) {
			res = "LastName field cannot be empty";
		} else if (lname.length() > 50) {
			res = "LastName Field cannot exceed 50 characters";
		} else if (!Character.isLetter(lname.charAt(0))) {
			res = "First character must be a letter";
		} else if (!n) {
			res = "LastName cannot have digits or special characters";
		}
		errorMsgs.setLnameError(res);
	}

	public void validateMobile(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String pn = register.getMobile();
		final String PN_PATTERN = "\\d{10}";
		Pattern pattern = Pattern.compile(PN_PATTERN);
		Matcher matcher = pattern.matcher(pn);
		if (pn.equals("")) {
			res = "The Contact number Field cannot be empty";
		} else if (pn.length() != 10) {
			res = "The Contact number must be 10 digits";
		}
		else if (!matcher.matches()) {
			res = "Invalid Contact number";
		}
		errorMsgs.setMobileError(res);
	}

	public void validateUtaId(User register, UserErrorMsgs errorMsgs) {
		String res = "";
		String uta = register.getUtaID();
		final String UTA_PATTERN = "^100\\d{7}";
		Pattern pattern = Pattern.compile(UTA_PATTERN);
		Matcher matcher = pattern.matcher(uta);
		if (uta.equals("")) {
			res = "The UTA ID number cannot be empty";
		} else if (uta.length() != 10) {
			res = "The UTA ID must be 10 digits";
		} else if (!matcher.matches()) {
			res = "Invalid UTA ID";
		}
		errorMsgs.setUtaIDError(res);
	}

	public void validateUserNamePassword(User login, UserErrorMsgs errorMsgs) {
		String res = "";
		String un = login.getUserName();
		String pass = login.getPassword();
		if (un.equals("") || pass.equals("")) {
			res = "The User Name or password field cannot be empty";
		} else if (!UserDAO.validateLoginCredentials(un, pass)) {
			res = "The User Name and (or) Password do not match";
		}

		errorMsgs.setUserNamePasswordError(res);
	}
	public void validateForPasswordReset(User resetPassword, UserErrorMsgs resetPassErrMsgs) {
		String utaID = resetPassword.getUtaID();
		String securityans = resetPassword.getSecurityans();
		String pass = resetPassword.getPassword();
		String cpass = resetPassword.getCpassword();
		String res = "";
		if (utaID.equals("") || securityans.equals("") || pass.equals("") || cpass.equals("")) {
			res = "Fill out all the fields";
		} else if (!UserDAO.validateForPasswordReset(utaID, securityans)) {
			res = "Incorrect security answer";
		}
		resetPassErrMsgs.setResetPasswordError(res);

	}
	
	public void validateAddress(User register, UserErrorMsgs errorMsgs ) {
		String res="";
		String un = register.getAddress();
		if (un.equals("")) {
			res = "The Address Field cannot be empty";
		} else if (!(un.length()>5 && un.length()<20)) {
			res = "The Address Field must be at least 5 characters and should not exceed 20 characters";
		}
		errorMsgs.setAddressError(res);
	}
}
