package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import project.model.Facility;
import project.model.FacilityErrorMsgs;


@RunWith(JUnitParamsRunner.class)
public class FacilityTest {
	Facility facility;
	FacilityErrorMsgs facerr;
	
	@Before
	public void setUp() throws Exception {
		facility = new Facility();
		facerr = new FacilityErrorMsgs();
		
	}
	

	@Test
	@FileParameters("src/tests/facilityTC.csv")
	public void test(String venue, String type, String name, String interval, String duration, String venueError, String typeError, String nameError, String intError, String duraError, String error) {
		
		facility.setFacility(name, type, interval, duration, venue);
		facility.validateFacility(facility, facerr);
		assertEquals(facerr.getVenueError() , venueError);
		assertEquals(facerr.getFacilityNameError(), nameError);
		assertEquals(facerr.getFacilityTypeError(), typeError);
		assertEquals(facerr.getDurationError(), duraError);
		assertEquals(facerr.getIntervalError(), intError);
		assertEquals(facerr.getErrorMsg(), error);
		
	}

}
