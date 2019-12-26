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
import project.data.ReservationDAO;
import project.model.MAR;
import project.model.Reservation;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepairerTest extends BusinessFunctions {
	 public static String url, sharedUIMapPath,username,password,id;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private WebDriver driver;
	 private FacilityDAO fdao = new FacilityDAO();
	 private ReservationDAO rdao = new ReservationDAO();
	 
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
			username = prop.getProperty("Repairer_Username");
			password = prop.getProperty("Repairer_Password");
			id = prop.getProperty("Repairer_id");
			url = prop.getProperty("url");
			sharedUIMapPath = prop.getProperty("SharedUIMap");
			prop.load(new FileInputStream(sharedUIMapPath));
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
			
			
	  }
	 @Test
	 public void A_registerRepairer()
	 {
		 driver.get(url);
		 MAC_BF_Register(driver,"1001789123","repairer@uta.edu","Password@123","Password@123","Replname","Repfname","Repairer"
				 ,"9001234567","Repairer address","Repairer Place");
		 driver.get(url);
		 MAC_BF_Login(driver,"repairer@uta.edu","Password@123");
		 assertEquals("Welcome, Repairer!",driver.findElement(By.xpath(prop.getProperty("H1_Repairer_Welcome"))).getText());
	 }
	 @Test
	 public void viewRepairerScheduleTest()
	 {
		 	 driver.get(url);
		 	 MAC_BF_Login(driver, username,password);
		 	
		 	 driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_RepairerSchedule"))).click();
		 	 String marNum = driver.findElement(By.xpath(prop.getProperty("TD_Repairer_ViewMarNum"))).getText();
		 	 String facName = driver.findElement(By.xpath(prop.getProperty("TD_Repairer_ViewFacName"))).getText();
		 	 String date = driver.findElement(By.xpath(prop.getProperty("TD_Repairer_ViewDate"))).getText();
		 	 ArrayList<MAR> arr = fdao.getAssignedMars(id);
		 	 try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_Repaired_AssignedRepaires"))).findElements(By.tagName("tr"));
		 	for(int i=2; i<=rows.size(); i++)
		 	{
		 		System.out.println("Inside for");
		 		assertEquals(arr.get(i-2).getMar_number(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[1]")).getText());
		 		assertEquals(arr.get(i-2).getFacility_type().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[2]")).getText().trim());
		 		assertEquals(arr.get(i-2).getFacility_name().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[3]")).getText().trim());
		 		assertEquals(arr.get(i-2).getDesc().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[4]")).getText().trim());
		 		assertEquals(arr.get(i-2).getUrgency().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[5]")).getText().trim());
		 		assertEquals(arr.get(i-2).getAssigned_date(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[6]")).getText());
		 	}
		 	driver.findElement(By.xpath(prop.getProperty("Lnk_Repairer_Reserve"))).click();
			 
		 	String methodName= new Throwable().getStackTrace()[0].getMethodName();
		 	 takeScreenshot(driver,methodName+" viewRepairerScheduleTC01");
			 assertEquals(marNum, driver.findElement(By.xpath(prop.getProperty("Td_Repairer_MarNum"))).getText());
			 assertEquals(facName, driver.findElement(By.xpath(prop.getProperty("Td_Repairer_FacName"))).getText());
			 assertEquals(date, driver.findElement(By.xpath(prop.getProperty("Td_Repairer_Date"))).getText());
			 driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_Logout"))).click();
	 }
	 
	 @Test
	 @FileParameters("src/selenium/reserve.csv")
	 public void reserveForRepair(String TcNum, String MarNum, String timeSlot, String msg)
	 {
		 driver.get(url);
	 	 MAC_BF_Login(driver, username,password);
	 	driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_RepairerSchedule"))).click();
	 	List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_Repairer_ListOfReservedRepairs"))).findElements(By.tagName("tr"));
	 	 
	 	for(int i=2; i<=rows.size(); i++)
	 	 {
	 		 
	 		 if(driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase(MarNum))
	 		 {
	 			 driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[7]/a")).click();
	 			 assertEquals(msg, driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText());
	 			
	 			 if(!(timeSlot.isEmpty()))
	 			 {
	 				 
	 				 new Select (driver.findElement(By.xpath(prop.getProperty("Select_Repairer_ReserveTimeslot")))).selectByValue(timeSlot);
	 				 driver.findElement(By.xpath(prop.getProperty("Btn_Repaired_ResrveSubmit"))).click();
	 				 assertEquals("Successfully Done!",driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText());
	 				String methodName= new Throwable().getStackTrace()[0].getMethodName();
	 			 	 takeScreenshot(driver,methodName+" ReserveFacility"+TcNum);
	 			 }
	 			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			 	 takeScreenshot(driver,methodName+" ReserveFacility"+TcNum);
	 			 break;
	 		 }
	 	 }
	 	driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_Logout"))).click();
	 }
	 @Test
	 @FileParameters("src/selenium/reserveCancel.csv")
	 public void cancelReservation(String TcNum, String RepairerId, String MarNum, String msg)
	 {
		 driver.get(url);
	 	 MAC_BF_Login(driver, username,password);
	 	 driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_ViewOrModify"))).click();
	 	 assertEquals("Your Reservations",driver.findElement(By.xpath(prop.getProperty("Td_Repairer_ViewText"))).getText());
	 	List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_Repairer_ListOfReservedRepairs"))).findElements(By.tagName("tr"));
	 	 ArrayList<Reservation> arr = rdao.getAssignedreservations(RepairerId);
	 	for(int i=2; i<=rows.size(); i++)
	 	{
	 		assertEquals(arr.get(i-2).getMar_id(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[2]")).getText());
	 		assertEquals(arr.get(i-2).getReservation_id(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[1]")).getText());
	 		assertEquals(arr.get(i-2).getFacility_name().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[3]")).getText().trim());
	 		assertEquals(arr.get(i-2).getDate(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[4]")).getText());
	 		assertEquals(arr.get(i-2).getTimeslot(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[5]")).getText());
	 		if(driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[2]")).getText().equalsIgnoreCase(MarNum))
	 		{
	 			driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[6]/a")).click();
	 			driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_CancelButtonSumbit"))).click();
	 			if(driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText().equalsIgnoreCase("Reservation Modify/Cancel"))
	 			{
	 				String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" CancelFacility"+TcNum);
	 				assertEquals(msg,driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_CancelErrorMsg"))).getText());
	 			}
	 			else
	 			{
	 				String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" CancelFacility"+TcNum);
	 				assertEquals(msg, driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText());
	 			}
	 			break;	
	 		}
	 		
	 	}
	 	driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_Logout"))).click();
	 }
	 
	 @Test
	 @FileParameters("src/selenium/reserveModify.csv")
	 public void modifyReservation(String TcNum, String RepairerId, String MarNum, String timeSlot, String msg)
	 {
		 driver.get(url);
	 	 MAC_BF_Login(driver, username,password);
	 	 driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_ViewOrModify"))).click();
	 	 assertEquals("Your Reservations",driver.findElement(By.xpath(prop.getProperty("Td_Repairer_ViewText"))).getText());
	 	List<WebElement> rows= driver.findElement(By.xpath(prop.getProperty("Table_Repairer_ListOfReservedRepairs"))).findElements(By.tagName("tr"));
	 	 ArrayList<Reservation> arr = rdao.getAssignedreservations(RepairerId);
	 	for(int i=2; i<=rows.size(); i++)
	 	{
	 		assertEquals(arr.get(i-2).getMar_id(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[2]")).getText());
	 		assertEquals(arr.get(i-2).getReservation_id(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[1]")).getText());
	 		assertEquals(arr.get(i-2).getFacility_name().trim(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[3]")).getText().trim());
	 		assertEquals(arr.get(i-2).getDate(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[4]")).getText());
	 		assertEquals(arr.get(i-2).getTimeslot(), driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[5]")).getText());
	 		if(driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[2]")).getText().equalsIgnoreCase(MarNum))
	 		{
	 			driver.findElement(By.xpath(".//div[1]/table/tbody/tr["+i+"]/td[6]/a")).click();
	 			new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Repairer_ModifyOrCancelTime")))).selectByValue(timeSlot);
	 			driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ModifyButtonSubmit"))).click();
	 			
	 			if(driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText().equalsIgnoreCase("Reservation Modify/Cancel"))
	 			{
	 				String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" ModifyFacility"+TcNum);
	 				
	 				assertEquals(msg,driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ModifyErrMsg"))).getText());
	 			}
	 			else
	 			{
	 				String methodName= new Throwable().getStackTrace()[0].getMethodName();
				 	 takeScreenshot(driver,methodName+" ModifyFacility"+TcNum);
	 				assertEquals(msg, driver.findElement(By.xpath(prop.getProperty("Repaired_HeadTitle"))).getText());
	 			}
	 			break;	
	 		}
	 		
	 	}
	 	driver.findElement(By.xpath(prop.getProperty("Nav_Repairer_Logout"))).click();
	 }
	 
	  @After
	  public void tearDown() throws Exception {
		  
	    driver.quit();

	  }

}

