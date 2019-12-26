package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BusinessFunctions {

	public static Properties prop;
	public void setup() throws FileNotFoundException, IOException
    {
    	prop  = new Properties();
    	prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
    }
	public void takeScreenshot(WebDriver driver, String screenshotname) {
		  try
		  {
			  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
			  FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
		  }
		  catch(IOException e) {}
		  try {
//			  Change the delay value to 1_000 to insert a 1 second delay after 
//			  each screenshot
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
 }
	public void MAC_BF_Register(WebDriver driver, String UTAID,String UserName, String Password, String ConfirmPassword, 
			String LastName, String FirstName,String RoleType, String ContactNumber, String Address, String SecurityQuestion) {
		driver.findElement(By.xpath(prop.getProperty("Btn_User_Register"))).click();
			driver.findElement(By.xpath(prop.getProperty("User_Register_UTAID"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_UTAID"))).sendKeys(UTAID);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_UserName"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_UserName"))).sendKeys(UserName);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_Password"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_Password"))).sendKeys(Password);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_ConfirmPassword"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_ConfirmPassword"))).sendKeys(ConfirmPassword);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_LastName"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_LastName"))).sendKeys(LastName);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_FirstName"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_FirstName"))).sendKeys(FirstName);
		   
		   new Select(driver.findElement(By.xpath(prop.getProperty("User_Register_RoleType")))).selectByVisibleText(RoleType);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_ContactNumber"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_ContactNumber"))).sendKeys(ContactNumber);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_Address"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_Address"))).sendKeys(Address);
		   driver.findElement(By.xpath(prop.getProperty("User_Register_SecurityQuestion"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("User_Register_SecurityQuestion"))).sendKeys(SecurityQuestion);
		   try {
				  Thread.sleep(1000);
			} catch (InterruptedException e) {}
		   driver.findElement(By.xpath(prop.getProperty("Btn_Register_Register"))).click();
		  
		}
	public void MAC_BF_Login(WebDriver driver, String username, String password)
	{
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
		
	}
	public void MAC_BF_ResetPassword(WebDriver driver, String securityQuestion,String utaID, String newpassword, String confirmnewpassword)
	{
		driver.findElement(By.xpath(prop.getProperty("User_ResetPassword_Link"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_SecQuestion"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_SecQuestion"))).sendKeys(securityQuestion);
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_UTAID"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_UTAID"))).sendKeys(utaID);
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_NewPassword"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_NewPassword"))).sendKeys(newpassword);
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_ConfirmPassword"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_ResetPassword_ConfirmPassword"))).sendKeys(confirmnewpassword);
		try {
			
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_ResetPassword_Reset"))).click();
		
	}
	public void MAC_BF_assignMar(WebDriver driver, String assignedTo, String assignedDate, 
			String estimate)
	{		
		//String selectPath = prop.getProperty("Select_UnassignedMar_AssignedTo") +"/option["+assignedTo+"]";
		//driver.findElement(By.xpath(prop.getProperty("Select_UnassignedMar_AssignedTo"))).click();
		//driver.findElement(By.xpath(selectPath)).click();
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_UnassignedMar_AssignedTo")))).selectByValue(assignedTo);
		driver.findElement(By.xpath(prop.getProperty("Txt_UnassignedMar_AssignedDate"))).sendKeys(assignedDate);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_UnassignedMar_Estimate")))).selectByValue(estimate);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_UnassignedMar_Submit"))).click();
	}
	public void MAC_BF_reAssignMar(WebDriver driver, String assignedTo, String assignedDate, 
			String estimate, String desc)
	{
		//driver.findElement(By.xpath(prop.getProperty(""))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarDesc"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarDesc"))).sendKeys(desc);
		
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_asgMarAssignedTo")))).selectByValue(assignedTo);
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarAssignedDate"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarAssignedDate"))).sendKeys(assignedDate);
		
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_asgMarEstimate")))).selectByValue(estimate);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_asgMarModify"))).click();
		
	}
	public void MAC_BF_searchAssignedMar(WebDriver driver, String date)
	{
		driver.findElement(By.xpath(prop.getProperty("Nav_FM_AssignedMar"))).click();
		driver.findElement(By.xpath(prop.getProperty("Nav_FM_searchAssignedMar"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_searchAsgDate"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_searchAsgDate"))).sendKeys(date);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_searchwithassDate"))).click();
	}

	public void MAC_BF_searchMar(WebDriver driver, String date)
	{
		driver.findElement(By.xpath(prop.getProperty("Nav_FM_AssignedMar"))).click();
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Nav_FM_SearchAssignedMar"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_searchMarDate"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_searchMarDate"))).sendKeys(date);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_searchMarSearch"))).click();
	}
	public void MAC_BF_numberOfFacilities(WebDriver driver, String facName, String date, String timeSlot)
	{
		driver.findElement(By.xpath(prop.getProperty("Nav_FM_NumberofFacilities"))).click();
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_NumberofFaculitiesFN")))).selectByValue(facName);
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_NumberofFacilitiesDt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_NumberofFacilitiesDt"))).sendKeys(date);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_NumboerofFacilitiesTime")))).selectByValue(timeSlot);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_NumberofFacilitiesSearch"))).click();
		
	}
	public void MAC_BF_modifyUserRole(WebDriver driver, String FName, String LName, String address, String role)
	{
		
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FirstName"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_FirstName"))).sendKeys(FName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LastName"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_LastName"))).sendKeys(LName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Address"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Admin_Address"))).sendKeys(address);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Admin_RoleModify")))).selectByValue(role);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_Admin_ConfirmUpdate"))).click();
		
	}
	
	public void MAC_BF_reportMar(WebDriver driver, String facType, String facName, String urg, String desc)
	{
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_User_ReportFacType")))).selectByValue(facType);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_User_ReportFacName")))).selectByValue(facName);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_User_ReportUrg")))).selectByVisibleText(urg);
		driver.findElement(By.xpath(prop.getProperty("Txt_User_ReportDesc"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_User_ReportDesc"))).sendKeys(desc);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_User_ReportSubmit"))).click();
	}
	
	public void MAC_BF_FacNum(WebDriver driver, String FacType, String Date, String Time)
	{
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_NumberofFaculitiesFN")))).selectByValue(FacType);
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_NumberofFacilitiesDt"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_NumberofFacilitiesDt"))).sendKeys(Date);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_NumboerofFacilitiesTime")))).selectByValue(Time);
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_NumberofFacilitiesSearch"))).click();
	}
	
	public void MAC_BF_FacDetails(WebDriver driver, String FacName)
	{
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_FacDetFacName")))).selectByValue(FacName);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_FacDetSearch"))).click();
	}
	
	public void MAC_BF_AddFacility(WebDriver driver, String FacType, String FacName,
			String Venue, String Interval, String Duration)
	{
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacFacType"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacFacType"))).sendKeys(FacType);
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacFacName"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacFacName"))).sendKeys(FacName);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_AddFacVenue")))).selectByValue(Venue);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_AddFacInterval")))).selectByValue(Interval);
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_FM_AddFacDur")))).selectByValue(Duration);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_FM_AddFacSubmit"))).click();
	}
	public void MAC_BF_updateProfile(WebDriver driver, String pwd, String FName, String LName, String address)
	{
		driver.findElement(By.xpath(prop.getProperty("Txt_User_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_User_Password"))).sendKeys(pwd);
		driver.findElement(By.xpath(prop.getProperty("Txt_User_FirstName"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_User_FirstName"))).sendKeys(FName);
		driver.findElement(By.xpath(prop.getProperty("Txt_User_LastName"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_User_LastName"))).sendKeys(LName);
		driver.findElement(By.xpath(prop.getProperty("Txt_User_Address"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_User_Address"))).sendKeys(address);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {}
		driver.findElement(By.xpath(prop.getProperty("Btn_User_ConfirmUpdate"))).click();
	}

}
