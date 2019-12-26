package project.model;

public class FacilityErrorMsgs {

	private String errorMsg ="";
	private String facilityTypeError="";
	private String facilityNameError="";
	private String venuError="";
	private String intervalError="";
	private String durationError="";
	
	
	public String getIntervalError() {
		return intervalError;
	}

	public void setIntervalError(String intervalError) {
		this.intervalError = intervalError;
	}

	public String getDurationError() {
		return durationError;
	}

	public void setDurationError(String durationError) {
		this.durationError = durationError;
	}


	
	public void setErrorMsg() {
		if (!facilityTypeError.equals("") || !facilityNameError.equals("") || !venuError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public void setFacilityTypeError(String facilityTypeError)
	{
		this.facilityTypeError = facilityTypeError;
	}
	
	public String getFacilityTypeError()
	{
		return this.facilityTypeError;
	}
	
	public void setFacilityNameError(String facilityNameError)
	{
		this.facilityNameError = facilityNameError;
		
	}
	
	public String getFacilityNameError()
	{
		return this.facilityNameError;
	}
	
	public void setVenueError(String venueError)
	{
		this.venuError = venueError;
	}
	
	public String getVenueError()
	{
		return this.venuError;
	}
	
	public String getErrorMsg()
	{
		return this.errorMsg;
	}
}
