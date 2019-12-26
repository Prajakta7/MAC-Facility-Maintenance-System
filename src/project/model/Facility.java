package project.model;



public class Facility {




	private String facility_name;
	private String facility_type;
	private String interval;
	private String duration;
	private String venue;
	


	public void setFacility (String facility_name, String facility_type,String interval, String duration, String venue) {
		setFacilityName(facility_name);
		setFacilityType(facility_type);
		setInterval(interval);
		setDuration(duration);
		setVenue(venue);
	}
	
	
	public void setFacilityName(String facility_name) {
		 this.facility_name = facility_name;
		
	}
	public String getFacilityName()
	{
		return facility_name;
	}
	
	
	public void setFacilityType(String facility_type) {
		 this.facility_type = facility_type;
		
	}
	public String getFacilityType()
	{
		return facility_type;
	}
	
	
	public void setInterval(String interval) {
		 this.interval = interval;
		
	}
	public String getInterval()
	{
		return interval;
	}
	
	
	
	public void setDuration(String duration) {
		 this.duration = duration;
		
	}
	public String getDuration()
	{
		return duration;
	}
	
	
	public void setVenue(String venue) {
		 this.venue = venue;
		
	}
	public String getVenue()
	{
		return venue;
	}
	
	public void validateFacility (Facility facility, FacilityErrorMsgs errorMsgs) {
		errorMsgs.setFacilityTypeError(validateFacilityType(facility.getFacilityType()));
		errorMsgs.setFacilityNameError(validateFacilityName(facility.getFacilityName()));
		errorMsgs.setVenueError(validateVenue(facility.getVenue()));
		errorMsgs.setIntervalError(validateInterval(facility.getInterval()));
		errorMsgs.setDurationError(validateDuration(facility.getDuration()));
		errorMsgs.setErrorMsg();
	}


	private String validateDuration(String duration2) {
		String res = "";
		if(duration2.equalsIgnoreCase(""))
			res = "No Value";
		return res;
	}


	private String validateInterval(String interval2) {
		String res="";
		if(interval2.equalsIgnoreCase(""))
			res ="No Value";

		return res;
	}


	private String validateVenue(String venue) {
		String result="";
		if (!stringSize(venue,3,16))
			result= "Your Venue Name must between 3 and 16 characters";
		return result;
	}


	private String validateFacilityName(String facilityName) {
		String result="";
		if (!stringSize(facilityName,3,16))
			result= "Your Facility Name must between 3 and 16 characters";
		
		return result;
	}


	private String validateFacilityType(String facilityType) {
		String result="";
		if (!stringSize(facilityType,3,16))
			result= "Your Facility Type must between 3 and 16 characters";
		return result;
	}

	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	
	
}
