package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.ArrayList;
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

import org.junit.runners.*;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminTest extends BusinessFunctions {
	 public static String url, sharedUIMapPath,username,password;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private WebDriver driver;
	 private UserDAO udao = new UserDAO();
	 ArrayList<User> NameSearch ;
	 
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
			//username = prop.getProperty("Admin_Username");
			//password = prop.getProperty("Admin_Password");
			url = prop.getProperty("url");
			sharedUIMapPath = prop.getProperty("SharedUIMap");
			prop.load(new FileInputStream(sharedUIMapPath));
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
		    
		    
			
			
	  }
	 
	 @Test
	 public void adminRegister()
	 {
		 driver.get(url);
		 MAC_BF_Register(driver,"1001234567", "admin@uta.edu", "Password@123","Password@123","Alname","Afname","Admin","9123456780",
	 			 "admin address","admin place"); 
	 }
	 @Test
	 @FileParameters("src/selenium/modifyUser.csv")
	 public void changeRolesTest(String tcNum, String usernameSearch, String roleSearch, String UnSearchErr, String FName, String Lname,
			 String address, String role, String FNameErr, String LNameErr)
	 {
		 	 driver.get(url);
		 	 MAC_BF_Login(driver, "admin@uta.edu","Password@123");		 	
			 driver.findElement(By.xpath(prop.getProperty("Nav_Admin_ModifyUsers"))).click();
			 driver.findElement(By.xpath(prop.getProperty("Nav_Admin_ModifyUsersSearch"))).click();
			 
			 if(!usernameSearch.equalsIgnoreCase(""))
			 {
				 driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Username"))).clear();
				 driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Username"))).sendKeys(usernameSearch);
				 try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {}
				 driver.findElement(By.xpath(prop.getProperty("Btn_Admin_UsernameSearch"))).click();
				 String url = "http://localhost:8080/project/UserController?action=searchByUsername&username="+usernameSearch;
				 if(url.equals(driver.getCurrentUrl()))
				 {
					 assertEquals(UnSearchErr, driver.findElement(By.xpath(prop.getProperty("H6_Admin_UnError"))).getText());
					 String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" AdminModifyUserRoleTC"+tcNum);
				 }
				 else
				 {
					 driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_ModifyProfile"))).click();
					 NameSearch = udao.getUserRoleUsername(usernameSearch);
					 assertEquals(NameSearch.get(0).getUtaID(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_UtaId"))).getAttribute("value"));
					 assertEquals(NameSearch.get(0).getFirstName(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FirstName"))).getAttribute("value"));
					 assertEquals(NameSearch.get(0).getLastName(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LastName"))).getAttribute("value"));
					 assertEquals(NameSearch.get(0).getAddress(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Address"))).getAttribute("value"));
					 assertEquals(NameSearch.get(0).getRoleType(), driver.findElement(By.xpath(prop.getProperty("Select_Admin_RoleModify"))).getAttribute("value"));
					 MAC_BF_modifyUserRole(driver, FName, Lname, address, role);
					 String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" AdminModifyUserRole(usingUname)TC"+tcNum);
				 	try {
						  Thread.sleep(5000);
					} catch (InterruptedException e) {}
				 	// System.out.println(driver.getCurrentUrl());
					 if(driver.findElement(By.xpath(prop.getProperty("H1_Admin_UpdateProfText"))).getText().equalsIgnoreCase("Update Profile"))
					 {
						 assertEquals(FNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FnError"))).getAttribute("value"));
						 assertEquals(LNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LnError"))).getAttribute("value"));
					 }
				 }
					 
			 }
			 
			 else
			 {
				 new Select(driver.findElement(By.xpath(prop.getProperty("Select_Admin_Role")))).selectByValue(roleSearch);
				 try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {}
				 driver.findElement(By.xpath(prop.getProperty("Btn_Admin_RoleSearch"))).click();
				 driver.findElement(By.xpath(prop.getProperty("Lnk_Admin_ModifyProfile"))).click();
				 ArrayList<User> RoleSearch = udao.getUserRoleType(roleSearch);
				 assertEquals(RoleSearch.get(0).getUtaID(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_UtaId"))).getAttribute("value"));
				 assertEquals(RoleSearch.get(0).getFirstName(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FirstName"))).getAttribute("value"));
				 assertEquals(RoleSearch.get(0).getLastName(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LastName"))).getAttribute("value"));
				 assertEquals(RoleSearch.get(0).getAddress(), driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Address"))).getAttribute("value"));
				 assertEquals(RoleSearch.get(0).getRoleType(), driver.findElement(By.xpath(prop.getProperty("Select_Admin_RoleModify"))).getAttribute("value"));
				 MAC_BF_modifyUserRole(driver, FName, Lname, address, role);
				 
				 if(driver.findElement(By.xpath(prop.getProperty("H1_Admin_UpdateProfText"))).getText().equalsIgnoreCase("Update Profile"))
				 {
					 String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" AdminModifyUserRole(usingRole)TC"+tcNum);
					 assertEquals(FNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FnError"))).getAttribute("value"));
					 assertEquals(LNameErr, driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LnError"))).getAttribute("value"));
				 }
				 
				 
			 }
			 
			 driver.findElement(By.xpath(prop.getProperty("Nav_Admin_UpdateProfile"))).click();
			 assertEquals(driver.findElement(By.xpath(prop.getProperty("H1_Admin_UpdateProfText"))).getText(), "Update Profile");
			 driver.findElement(By.xpath(prop.getProperty("Nav_Admin_Logout"))).click();
			 
			 
	 }
	 
	 
	  @After
	  public void tearDown() throws Exception {
		  
	    driver.quit();
	  }

}

