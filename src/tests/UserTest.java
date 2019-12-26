package tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import project.model.User;
import project.model.UserErrorMsgs;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	@Test
	@FileParameters("src/tests/UserTestModified.csv")
	public void test1(String tcNum, String UTAID, String username, String password, String Cpassword,
			 String LastName, String FirstName,String Roletype, String ContactNumber,String Address,String 
			 SecurityQuestion,String UTAID_errormsgs, String username_errormsgs, String password_errormsgs, 
			 String Cpassword_errormsgs,String LastName_errormsgs, String FirstName_errormsgs, 
			 String ContactNumber_errormsgs,String roletype_errormsgs, String address_errormsgs, 
			 String resetpswd_errormsgs, String usernamepswd_errormsgs) {
		
		UserErrorMsgs e1=new UserErrorMsgs();
		User u1=new User();
		
		u1.setUserName(username);
		u1.setPassword(password);
		u1.setCpassword(Cpassword);
		u1.setFirstName(FirstName);
		u1.setLastName(LastName);
		u1.setRoleType(Roletype);
		u1.setMobile(ContactNumber);
		u1.setUtaID(UTAID);
		u1.setAddress(Address);
		u1.setSecurityans(SecurityQuestion);
		
		u1.validateUserName(u1, e1);
		u1.validatePassword(u1, e1);
		u1.validateCpassword(u1, e1);
		u1.validateFirstName(u1, e1);
		u1.validateLastName(u1, e1);
		u1.validateMobile(u1, e1);
		u1.validateUtaId(u1,e1);
		u1.validateUserNamePassword(u1, e1);
		u1.validateForPasswordReset(u1, e1);
		u1.validateAddress(u1, e1);
		u1.validateRoleType(u1, e1);
		//u1.validateID(u1,e1);

		
		assertEquals(username_errormsgs,e1.getUserNameError());
		assertEquals(password_errormsgs,e1.getPasswordError());
		assertEquals(Cpassword_errormsgs,e1.getCpasswordError());
		assertEquals(FirstName_errormsgs,e1.getFnameError());
		assertEquals(LastName_errormsgs,e1.getLnameError());
		assertEquals(ContactNumber_errormsgs,e1.getMobileError());
		assertEquals(UTAID_errormsgs,e1.getUtaIDError());
		assertEquals(usernamepswd_errormsgs,e1.getUserNamePasswordError());
		assertEquals(resetpswd_errormsgs,e1.getResetPasswordError());
		assertEquals(address_errormsgs,e1.getAddressError());
		assertEquals(roletype_errormsgs,e1.getRoleTypeError());
		//assertEquals(UTAID_errormsgs,e1.getidError());
		}
}
