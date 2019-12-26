package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import project.data.UserDAO;
import project.model.User;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest extends BusinessFunctions {
	 public static String url, sharedUIMapPath,username,password;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private WebDriver driver;
	 private List<User> lst;
	 
	 @BeforeClass
		public static void setUpBeforeClass() throws Exception {
		    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	  
	 }
	 @Before
	  public void setUp() throws Exception
	  {
		 System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		    driver = new FirefoxDriver();
		    driver.manage().window().maximize();
		    prop = new Properties();	  
		    prop.load(new FileInputStream("./Configuration/MAC_Configuration.properties"));
			//username = prop.getProperty("User_Username");
			//password = prop.getProperty("User_Password");
			url = prop.getProperty("url");
			sharedUIMapPath = prop.getProperty("SharedUIMap");
			prop.load(new FileInputStream(sharedUIMapPath));
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
			
			
	  }
	 @Test
	 public void A_SuccessUserRegister()
	 {
		 driver.get(url);
		 MAC_BF_Register(driver,"1001222345","user@uta.edu","Password@123","Password@123","Ulname","Ufname","Student"
				 ,"9444098765","Student address","Student Place");
	 }
	 @Test
	 @FileParameters("src/selenium/RegisterTest.csv")
	 public void B_RegistrationTests(String tcNum, String UTAID, String username, String password, String Cpassword,
			 String LastName, String FirstName,String Roletype, String ContactNumber,String Address,String 
			 SecurityQuestion,String UTAID_errormsgs, String username_errormsgs, String password_errormsgs, 
			 String Cpassword_errormsgs,String LastName_errormsgs, String FirstName_errormsgs, String ContactNumber_errormsgs)
	 {
		 driver.get(url);
		 MAC_BF_Register(driver,UTAID, username,password,Cpassword,LastName,FirstName,Roletype,ContactNumber,
				 Address,SecurityQuestion);
		 String methodName= new Throwable().getStackTrace()[0].getMethodName();
	 	 takeScreenshot(driver,methodName+" TC"+tcNum);
		 
	 	 assertEquals(UTAID_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_IdErr"))).getAttribute("value"));
		 assertEquals(username_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_UnErr"))).getAttribute("value"));
		 assertEquals(password_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_PasswordErr"))).getAttribute("value"));
		 assertEquals(Cpassword_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_MatchPwdErr"))).getAttribute("value"));
		 assertEquals(LastName_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_LnErr"))).getAttribute("value"));
		 assertEquals(FirstName_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_FnErr"))).getAttribute("value"));
		 assertEquals(ContactNumber_errormsgs,driver.findElement(By.xpath(prop.getProperty("Txt_User_ContactErr"))).getAttribute("value"));
	 }
	 @Test
	 @FileParameters("src/selenium/LoginTest.csv")
	 public void C_loginTest(String tcNum, String Uname, String Pword, String errorMsgs)
	 {
		 driver.get(url);
	 	 MAC_BF_Login(driver, Uname,Pword);
	 	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	 	 takeScreenshot(driver,methodName+" TC"+tcNum);
	 	 try
	 	 {
	 		 assertEquals(errorMsgs, driver.findElement(By.xpath(prop.getProperty("Txt_User_AllError"))).getAttribute("value"));
	 	 }
	 	 catch(NoSuchElementException e)
	 	 {
	 		 
	 	 }
	 }
	 
	 @Test
	 @FileParameters("src/selenium/reportMar.csv")
	 public void D_reportMarTest(String tcNum, String facType, String facName, String Urg, String desc, String descError)
	 {
		 	 driver.get(url);
		 	 MAC_BF_Login(driver, "user@uta.edu","Password@123");
		 	 
		 	 driver.findElement(By.xpath(prop.getProperty("Nav_User_ReportMar"))).click();
		 	MAC_BF_reportMar(driver, facType, facName, Urg, desc);
		 	String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" TC"+tcNum);
		 	if(driver.findElement(By.xpath(prop.getProperty("H1_User_ReportMarHead"))).getText().equals("Report MAR"))
		 	{
		 		assertEquals(descError, driver.findElement(By.xpath(prop.getProperty("Txt_User_DescError"))).getAttribute("value"));
		 	}
		 	
		 	driver.findElement(By.xpath(prop.getProperty("Nav_User_Logout"))).click();
		 	//driver.findElement(By.xpath(prop.getProperty("Nav_User_Logout"))).click();
	 }
	 
	 @Test
	 @FileParameters("src/selenium/ResetPasswordTest.csv")
	 public void E_ResetPasswordTest(String tcNum, String securityQuestion, String utaID, String newpassword, String confirmnewpassword, String resetPasswordError)
	 {
		 driver.get(url);
	 	 MAC_BF_ResetPassword(driver, securityQuestion, utaID, newpassword, confirmnewpassword);
	 	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	 	 takeScreenshot(driver,methodName+" TC"+tcNum);
	 	 try
	 	 {
	 		 assertEquals(resetPasswordError, driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_Error"))).getAttribute("value"));
	 	 }
	 	 catch(NoSuchElementException e)
	 	 {
	 		 
	 	 }
	 	//driver.findElement(By.xpath(prop.getProperty("Nav_User_Logout"))).click();
	 }
	 
	 @Test
	 @FileParameters("src/selenium/updateProfile.csv")
	 public void F_profileUpdateTesting(String tcNum, String utaId,String uname, String pwd, String FName, String LName, String Addr, String PwdErr, String FNameErr, String LNameErr)
	 {
		 	 driver.get(url);
		 	MAC_BF_Login(driver, "user@uta.edu","Password@123");
		 	 
		 	 driver.findElement(By.xpath(prop.getProperty("Nav_User_UpdateProfile"))).click();
		 	 
		 	MAC_BF_updateProfile(driver, pwd, FName, LName, Addr);
		 	UserDAO uDAO = new UserDAO();
		 	
		 	try {
				lst = uDAO.list(uname);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		 	String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" TC"+tcNum);
		 	if(driver.findElement(By.xpath(".//div[1]/div/h1")).getText().equals("Update Profile"))
		 	{
		 		assertEquals(PwdErr, driver.findElement(By.xpath(prop.getProperty("Txt_User_PwdErrMsg"))).getAttribute("value"));
		 		assertEquals(FNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_User_FNameErrMsg"))).getAttribute("value"));
		 		assertEquals(LNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_User_LNameErrMsg"))).getAttribute("value"));
		 		
		 		assertEquals(lst.get(0).getUtaID(), driver.findElement(By.xpath(prop.getProperty("Txt_User_utaid2"))).getAttribute("value"));
		 		assertEquals(lst.get(0).getPassword(), driver.findElement(By.xpath(prop.getProperty("Txt_User_Password2"))).getAttribute("value"));
		 		assertEquals(lst.get(0).getFirstName(), driver.findElement(By.xpath(prop.getProperty("Txt_User_FirstName2"))).getAttribute("value"));
		 		assertEquals(lst.get(0).getLastName(), driver.findElement(By.xpath(prop.getProperty("Txt_User_LastName2"))).getAttribute("value"));
		 		assertEquals(lst.get(0).getAddress(), driver.findElement(By.xpath(prop.getProperty("Txt_User_Address2"))).getAttribute("value"));
		 		
		 	}
		 	else 
		 	{
		 		assertEquals("Successfully Done!", driver.findElement(By.xpath(prop.getProperty("H1_User_ReportMarHead"))).getText());

		 	}
		 	driver.findElement(By.xpath(prop.getProperty("Nav_User_Logout"))).click();
		 	
	 }

	 
	 
	  @After
	  public void tearDown() throws Exception {
		  
	    driver.quit();
	    
	  }

}

