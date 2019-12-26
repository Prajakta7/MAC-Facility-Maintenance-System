package project.model;


public class ReservationErrorMsgs {
	private String errorMsg;
	private String timeslotError;
	private String dateError;
	private String repairer_idError;
	private String mar_idError;
	private String facility_nameError;
	private String reservation_idError;
	private String facility_typeError;
	
	public String getRepairer_idError() {
		return repairer_idError;
	}
	public void setRepairer_idError(String repairer_idError) {
		this.repairer_idError = repairer_idError;
	}
	public String getMar_idError() {
		return mar_idError;
	}
	public void setMar_idError(String mar_idError) {
		this.mar_idError = mar_idError;
	}
	public String getFacility_nameError() {
		return facility_nameError;
	}
	public void setFacility_nameError(String facility_nameError) {
		this.facility_nameError = facility_nameError;
	}
	public String getReservation_idError() {
		return reservation_idError;
	}
	public void setReservation_idError(String reservation_idError) {
		this.reservation_idError = reservation_idError;
	}
	public String getFacility_typeError() {
		return facility_typeError;
	}
	public void setFacility_typeError(String facility_typeError) {
		this.facility_typeError = facility_typeError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String s) {
		
		
			if (!timeslotError.equals(""))
			{
				this.errorMsg = "Reservation can not be done as already a reservation exisits";
			}
			else if(!dateError.equals(""))
				{
				this.errorMsg = "Past Reservations cannot be cacelled or modified";
				}
			
		
		else
			this.errorMsg=s;
		
		
	}
	public String getDateError() {
		return dateError;
	}
	public void setDateError(String dateError) {
		this.dateError = dateError;
	}
	public String getTimeslotError() {
		return timeslotError;
	}
	public void setTimeslotError(String timeslot) {
		this.timeslotError = timeslot;
	}
	

	
}
