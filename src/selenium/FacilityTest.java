package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.SQLException;
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
import project.data.FacilityDAO;
import project.model.Facility;
import project.model.MAR;
import project.model.Reservation;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacilityTest extends BusinessFunctions {
	 public static String url, sharedUIMapPath,username,password;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private WebDriver driver;
	 private FacilityDAO fdao = new FacilityDAO();
	 ArrayList<MAR> searchAssigned ;
	 ArrayList<MAR> searchUnAsg ;
	 List<Reservation> check ;
	 List<Facility> facDetails;
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
			//username = prop.getProperty("FM_Username");
			//password = prop.getProperty("FM_Password");
			url = prop.getProperty("url");
			sharedUIMapPath = prop.getProperty("SharedUIMap");
			prop.load(new FileInputStream(sharedUIMapPath));
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
			
			
	  }
	 @Test
	 public void A_FMRegister()
	 {
		 driver.get(url);
		 MAC_BF_Register(driver,"1001098765","facmanager@uta.edu","Password@123","Password@123","FMlname","FMfname",
				 "Facility Manager","9123456780","Fm address","FM place");
	 }
	 @Test
	 @FileParameters("src/selenium/assignMar.csv")
	 public void B_unAssignedMarTest(String tcNum, String searchDate, String assignedTo, String assignedDate, String estimate, String error,
			 String assignWeek, String assignDateEr, String assignToError, String dateError, String estimateError)
	 {
		 	 driver.get(url);
		 	 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_UnassignedMar"))).click();
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_UnassignedMarSearch"))).click();
			 driver.findElement(By.xpath(prop.getProperty("Txt_UnassignedMar_DateSearch"))).clear();
			 driver.findElement(By.xpath(prop.getProperty("Txt_UnassignedMar_DateSearch"))).sendKeys(searchDate);
			 try {
				  Thread.sleep(1000);
			} catch (InterruptedException e) {}
			 driver.findElement(By.xpath(prop.getProperty("Btn_UnassignedMar_DateSearchSubmit"))).click();
			 
			 
			 if(driver.findElement(By.xpath(prop.getProperty("H1_FM_displayHead"))).getText().equals("Enter date to get List of UnAssigned MAR's"))
			 {
				 String methodName= new Throwable().getStackTrace()[0].getMethodName();
			 	 takeScreenshot(driver,methodName+" AssignMarTC"+tcNum);
				 assertEquals(driver.findElement(By.xpath(prop.getProperty("Txt_FM_dateError"))).getText(), dateError);
			 }
			 else
			 {
				 String mar_num = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_MarNum"))).getText();
				 String FacType = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_FacType"))).getText();
				 String Desc = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_Desc"))).getText();
				 String Urgency = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_Urgency"))).getText();
				 String RepBy = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_ReportedBy"))).getText();
				 String RepDate = driver.findElement(By.xpath(prop.getProperty("Td_UnassignedMar_RepDate"))).getText();
				 
				 searchUnAsg = fdao.getMarsU(searchDate);
				 List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_UnassignedMar_Table"))).findElements(By.tagName("tr"));
				 for(int i=2; i<=rows.size(); i++)
				 {
					 
					 String mar_num1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[1]"))).getText();
					 String FacType1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[2]"))).getText();
					 String Desc1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[4]"))).getText();
					 String Urgency1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[5]"))).getText();
					 String RepBy1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[6]"))).getText();
					 String RepDate1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[7]"))).getText(); 
					 assertEquals(mar_num1, searchUnAsg.get(i-2).getMar_number());
					 assertEquals(FacType1, searchUnAsg.get(i-2).getFacility_type());
					 assertEquals(Desc1, searchUnAsg.get(i-2).getDesc());
					 assertEquals(Urgency1.trim(), searchUnAsg.get(i-2).getUrgency().trim());
					 assertEquals(RepBy1, searchUnAsg.get(i-2).getReported_by());
					 assertEquals(RepDate1, searchUnAsg.get(i-2).getDate());
					 
				 }
				 
				 driver.findElement(By.xpath(prop.getProperty("Lnk_FM_assignView"))).click();
				 
				 assertEquals(mar_num, driver.findElement(By.xpath(prop.getProperty("TD_FM_GetMarNum"))).getText());
				 //assertEquals(FacName, driver.findElement(By.xpath(".//div[1]/form/table/tbody/tr[2]/td[2]/select/option[1]")).getAttribute("value"));
				 assertEquals(FacType, driver.findElement(By.xpath(prop.getProperty("Select_FM_GetFacType"))).getAttribute("value"));
				 assertEquals(Desc, driver.findElement(By.xpath(prop.getProperty("TD_FM_GetDesc"))).getAttribute("value"));
				 assertEquals(Urgency, driver.findElement(By.xpath(prop.getProperty("Select_FM_GetUrg"))).getText());
				 assertEquals(RepBy, driver.findElement(By.xpath(prop.getProperty("Txt_FM_repBy"))).getAttribute("value"));
				 assertEquals(RepDate, driver.findElement(By.xpath(prop.getProperty("Txt_Fm_GetRepDate"))).getAttribute("value"));
				 
				 MAC_BF_assignMar(driver, assignedTo, assignedDate, estimate);
				 
				 String methodName= new Throwable().getStackTrace()[0].getMethodName();
			 	 takeScreenshot(driver,methodName+" AssignMarTC"+tcNum); 
			 	 
				 assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Txt_FM_errMsg"))).getAttribute("value"));
				 assertEquals(assignWeek, driver.findElement(By.xpath(prop.getProperty("Txt_FM_assignWeekErr"))).getAttribute("value"));
				 assertEquals(assignDateEr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_assignDateErr"))).getAttribute("value"));
				 assertEquals(assignToError, driver.findElement(By.xpath(prop.getProperty("Txt_FM_assignToErr"))).getText());
				 assertEquals(dateError, driver.findElement(By.xpath(prop.getProperty("Txt_FM_DateErr"))).getAttribute("value"));
				 assertEquals(estimateError, driver.findElement(By.xpath(prop.getProperty("Txt_FM_EstimateErr"))).getText());
			 }
			 
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
	 }
	// @Test
	 @FileParameters("src/selenium/reAssignMar.csv")
	 public void reAssignedMarTest(String tcNum, String searchDate, String assignedTo, String assignedDate, String estimate, 
			 String desc, String error,String assignWeek, String assignDateEr,  String dateError, String descErr)
	 {
		 driver.get(url);
	 	 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
	 	MAC_BF_searchAssignedMar(driver, searchDate);
	 	searchAssigned = fdao.getMarsD(searchDate);
	 	if(driver.findElement(By.xpath(prop.getProperty("H1_FM_asgMarTitle"))).getText().equals("Enter date to get List of Assigned MAR's"))
		 {
			 String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" AssignMarTC"+tcNum);
			 assertEquals(driver.findElement(By.xpath(prop.getProperty("Span_FM_asgMarSearchDateErr"))).getText(), dateError);
		 }
	 	else
	 	{
	 		String mar_num = driver.findElement(By.xpath(prop.getProperty("TD_FM_asgMarNum"))).getText();
			 String FacType = driver.findElement(By.xpath(prop.getProperty("TD_FM_asgMarFacType"))).getText();
			 String FacName = driver.findElement(By.xpath(prop.getProperty("TD_FM_asgMarFacName"))).getText();
			 String RepBy = driver.findElement(By.xpath(prop.getProperty("TD_FM_asgMarRepBy"))).getText();
			 String RepDate = driver.findElement(By.xpath(prop.getProperty("TD_FM_asgMarRepDate"))).getText();
			 List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_FM_AsgMarTable"))).findElements(By.tagName("tr"));
			 System.out.println(rows.size());
			 for(int i=2; i<=rows.size(); i++)
			 {
				 
				 String mar_num1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[1]"))).getText();
				 String FacType1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[2]"))).getText();
				 String Desc1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[4]"))).getText();
				 String Urgency1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[5]"))).getText();
				 String RepBy1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[6]"))).getText();
				 String RepDate1 = driver.findElement(By.xpath((".//div[1]/table/tbody/tr["+i+"]/td[7]"))).getText(); 
				 assertEquals(mar_num1, searchAssigned.get(i-2).getMar_number());
				 assertEquals(FacType1, searchAssigned.get(i-2).getFacility_type());
				 assertEquals(Desc1, searchAssigned.get(i-2).getDesc());
				 assertEquals(Urgency1.trim(), searchAssigned.get(i-2).getUrgency().trim());
				 assertEquals(RepBy1, searchAssigned.get(i-2).getReported_by());
				 assertEquals(RepDate1, searchAssigned.get(i-2).getDate());
				 
			 }
			 
			 driver.findElement(By.xpath(prop.getProperty("Lnk_FM_viewAssigMarDetails"))).click();
			 
			 assertEquals(mar_num, driver.findElement(By.xpath(prop.getProperty("TD_FM_viewMarNum"))).getText());
			 assertEquals(FacType, driver.findElement(By.xpath(prop.getProperty("TD_FM_viewFacType"))).getAttribute("value"));
			 assertEquals(RepBy, driver.findElement(By.xpath(prop.getProperty("TD_FM_repBy"))).getAttribute("value"));
			 assertEquals(RepDate, driver.findElement(By.xpath(prop.getProperty("TD_FM_repDate"))).getAttribute("value"));
			 
			 MAC_BF_reAssignMar(driver, assignedTo, assignedDate, estimate, desc);
			 String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" reAssignMarTC"+tcNum); 
		 	 
		 	 assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarError"))).getAttribute("value"));
			 assertEquals(assignWeek, driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarAsgWeekErr"))).getAttribute("value"));
			 assertEquals(assignDateEr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarAsgDayErr"))).getAttribute("value"));
			 assertEquals(dateError, driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarDateError"))).getAttribute("value"));
			 assertEquals(descErr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_asgMarDescErr"))).getAttribute("value"));
			 //driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
	 	}
	 	
	 	driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
	 }
	 
	 //	@Test
		 public void repairerScheduleTest()
		 {
			 driver.get(url);
			 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_RepairerSchedule"))).click();
			 new Select (driver.findElement(By.xpath(prop.getProperty("Select_FM_RepairerScheduleSelect")))).selectByValue("1001552475");
			 try {
				  Thread.sleep(1000);
			} catch (InterruptedException e) {}
			 driver.findElement(By.xpath(prop.getProperty("Btn_FM_RepairerScheduleSubmit"))).click();
			 String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" ReAssignMarTC");
			 assertEquals("Repairer Schedule",driver.findElement(By.xpath(prop.getProperty("H2_FM_RepairerSchdHead"))).getText());
			 try {
				check = fdao.getRepairerSchedule("1001552475");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //System.out.println(check.size());
			 List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_FM_RepairedSchdTable"))).findElements(By.tagName("tr"));
			 for(int i=2; i<=rows.size(); i++)
			 {
				 System.out.println(check.get(i-2).getMar_id());
				 assertEquals(check.get(i-2).getMar_id(), driver.findElement(By.xpath(".//div[1]/center/table/tbody/tr["+i+"]/td[2]")).getText());
				 assertEquals(check.get(i-2).getFacility_name(), driver.findElement(By.xpath(".//div[1]/center/table/tbody/tr["+i+"]/td[3]")).getText());
				 assertEquals(check.get(i-2).getDate(), driver.findElement(By.xpath(".//div[1]/center/table/tbody/tr["+i+"]/td[4]")).getText());
				 assertEquals(check.get(i-2).getTimeslot(), driver.findElement(By.xpath(".//div[1]/center/table/tbody/tr["+i+"]/td[5]")).getText());
				 assertEquals(check.get(i-2).getRepairer_id(), driver.findElement(By.xpath(".//div[1]/center/table/tbody/tr["+i+"]/td[1]")).getText());
			 }
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
		 }
	//	@Test
		 @FileParameters("src/selenium/facilityNum.csv")
		 public void facilityNumbers(String TcNum, String FacilityType, String Date, 
				 String TimeSlot, String DateErr, String Result)
		 {
			 driver.get(url);
			 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_NumberofFacilities"))).click();
			 MAC_BF_FacNum(driver,FacilityType,Date,TimeSlot);
			 String num ="";
			 try {
				num = fdao.getFacilityNumbers(FacilityType, null, Date, TimeSlot);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(!Result.isEmpty())
			 {
				 assertEquals(Result, num);
			 }
			 String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" NumOfFacilities"+TcNum);
			 assertEquals(DateErr, driver.findElement(By.xpath(prop.getProperty("Span_FM_NumFacDateErr"))).getText());
			 assertEquals(Result, driver.findElement(By.xpath(prop.getProperty("H4_FM_NumFacResult"))).getText());
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
		 }
		 
		// @Test
		 public void facilityDetails()
		 {
			 driver.get(url);
			 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_FacilityDetails"))).click();
			 MAC_BF_FacDetails(driver, " CR 1");
			 String res = driver.findElement(By.xpath(prop.getProperty("Txt_FM_FacDetDisplay"))).getText();
			 
			try {
				facDetails = fdao.getFacilityDetails(" CR 1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" FacDetails");
			 for(int i=0; i<facDetails.size(); i++)
			 {
				 assertEquals(res.contains(facDetails.get(i).getFacilityType()), true);
				 assertEquals(res.contains(facDetails.get(i).getVenue()), true);
				 assertEquals(res.contains(facDetails.get(i).getInterval()), true);
				 assertEquals(res.contains(facDetails.get(i).getDuration()), true);
			 }
			 driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
		 }
	// @Test
	 @FileParameters("src/selenium/addFac.csv")
	 public void addFacility(String tcNum, String FacType, String FacName, String Venue, String interval, 
			 String Duration, String allErr, String typeErr, String nameErr)
	 {
		 driver.get(url);
		 MAC_BF_Login(driver, "facmanager@uta.edu","Password@123");
		 driver.findElement(By.xpath(prop.getProperty("Nav_FM_AddFacility"))).click();
		 MAC_BF_AddFacility(driver, FacType, FacName, Venue, interval, Duration);
		 String methodName= new Throwable().getStackTrace()[0].getMethodName();
	 	 takeScreenshot(driver,methodName+" AddFacility"+tcNum);
		 if(driver.findElement(By.xpath(prop.getProperty("H1_FM_displayHead"))).getText().equals("Add Facility"))
		 {
			 assertEquals(allErr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacAllErr"))).getAttribute("value"));
			 assertEquals(typeErr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacTypeErr"))).getAttribute("value"));
			 assertEquals(nameErr, driver.findElement(By.xpath(prop.getProperty("Txt_FM_AddFacNameErr"))).getAttribute("value"));
		 }
		 else
		 {
			 assertEquals(driver.findElement(By.xpath(prop.getProperty("H1_FM_displayHead"))).getText(), "Welcome, Facility Manager!");
		 }
		 driver.findElement(By.xpath(prop.getProperty("Nav_FM_Logout"))).click();
		 
	 }
	  @After
	  public void tearDown() throws Exception {
		  
	    driver.quit();
	    
	  }

}

