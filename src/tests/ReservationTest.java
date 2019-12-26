package tests;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import project.model.Reservation;
import project.model.ReservationErrorMsgs;


@RunWith(JUnitParamsRunner.class)
public class ReservationTest {
	Reservation res;
	ReservationErrorMsgs resErr;
	
	@Before
	public void setUp() throws Exception {
		
		res = new Reservation();
		resErr = new ReservationErrorMsgs();
		
	}

	@Test
	@FileParameters("src/tests/resrvationTC.csv")
	public void test(String marID, String repID, String date, String timeSlot, String facName, 
			String resId, String facType, String errMsg, String dateErr, String timeSlotErr,
			String repIdErr, String marIdErr, String facNamErr, String resIdErr,
			String facTyErr) throws ParseException {
		res.setReservation(repID, marID, facName, date, timeSlot, resId, facType);
		res.validateReservation(res, resErr);
		assertEquals(resErr.getRepairer_idError(),repIdErr);
		assertEquals(resErr.getMar_idError(),marIdErr);
		assertEquals(resErr.getFacility_nameError(),facNamErr);
		assertEquals(resErr.getReservation_idError(),resIdErr);
		assertEquals(resErr.getFacility_typeError(),facTyErr);
		assertEquals(resErr.getTimeslotError(),timeSlotErr);
		assertEquals(resErr.getDateError(), dateErr);
		assertEquals(resErr.getErrorMsg(),errMsg);
		
		
		
	}

}
