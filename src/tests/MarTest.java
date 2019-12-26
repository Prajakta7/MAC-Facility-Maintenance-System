package tests;

import static org.junit.Assert.*;

import project.data.FacilityDAO;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import project.model.MAR;
import project.model.MARErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class MarTest {
	MAR mar;
	MARErrorMsgs marErr;

	
	@Before
	public void setUp() throws Exception {
		mar = new MAR();
		marErr = new MARErrorMsgs();

		
	}

	
	@Test
	@FileParameters("src/tests/marTC.csv")
	public void test(String marNum, String FacilityType, String FacilityName, String desc,
			String date, String reportedBy, String assignedTo, String assignedDate, String estimate, String urgency,
			String descError, String estimateError, String assginedError, String dateError, String errMsg,
			String assignMoreErr, String assignWeekErr, String marNumError, String FacTypeErr,
			String FacNameErr, String RepDateErr, String RepErr, String UrgErr) throws ParseException {
		mar.setMar(marNum, FacilityType, FacilityName, desc, date, reportedBy, assignedTo, assignedDate, estimate, urgency);
		mar.validateMarAssignment(mar, marErr);
		//mar.validateAssignMore(assignedTo, assignedDate);
		//mar.validateAssignWeek(assignedTo, assignedDate);
		assertEquals(marErr.getAssigned_more(),assignMoreErr);
		assertEquals(marErr.getAssigned_week(), assignWeekErr);
		assertEquals(marErr.getDesc(),descError);
		assertEquals(marErr.getEstimate_of_repair(), estimateError);
		assertEquals(marErr.getAssigned_to(), assginedError);
		assertEquals(marErr.getAssigned_DateError(), dateError);
		assertEquals(marErr.getErrorMsg(), errMsg);
		assertEquals(marErr.getMarNumErr(), marNumError);
		assertEquals(marErr.getFacTypeErr(), FacTypeErr);
		assertEquals(marErr.getFacNamErr(), FacNameErr);
		assertEquals(marErr.getRepDateErr(),RepDateErr);
		assertEquals(marErr.getRepErr(), RepErr);
		assertEquals(marErr.getUrgErr(), UrgErr);
		
	}

}
