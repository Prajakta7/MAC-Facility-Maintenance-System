package project.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import project.data.ReservationDAO;

public class Reservation {
	
	private String mar_id;
	private String repairer_id;
	private String date;
	private String timeslot;
	private String facility_name;
	private String reservation_id;
	private String facility_type;
	
	
	
	ReservationDAO dao = new ReservationDAO();
	   
	
	public String getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getRepairer_id() {
		return repairer_id;
	}
	public void setRepairer_id(String repairer_id) {
		this.repairer_id = repairer_id;
	}
	public String getMar_id() {
		return mar_id;
	}
	public void setMar_id(String mar_id) {
		this.mar_id = mar_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	public String getFacility_type() {
		return facility_type;
	}
	public void setFacility_type(String facility_type) {
		this.facility_type = facility_type;
	}
	public void setReservation( String repairer_id,
	 String mar_id,
	 String facility_name,
	 String date,
	 String timeslot,
	 String reservation_id,
	 String facility_type)
	{
		setRepairer_id(repairer_id);
		setMar_id(mar_id);
		setFacility_name(facility_name);
		setDate(date);
		setTimeslot(timeslot);
		setReservation_id(reservation_id);
		setFacility_type(facility_type);
	}

	public String validateDate(String adate) throws ParseException
	{
		String result="";
		
		  Date date1=null;
			
			date1=new SimpleDateFormat("yyyy-MM-dd").parse(adate);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			dateFormat.format(date);
               
               if(date.before(date1))
               {
				   
				  result ="";
		        
			   } 
		  
			   else {
				    result ="Past Reservations cannot be cacelled or modified";
				    
			   }
			   return result;
	}
	public void validateReservation (Reservation res, ReservationErrorMsgs errorMsgs) throws ParseException {
		errorMsgs.setDateError(validateDate(res.getDate()));
			errorMsgs.setTimeslotError(validateTime(res));
			errorMsgs.setFacility_nameError(validateFacility_name(res.getFacility_name()));
			errorMsgs.setRepairer_idError(validateRepairer(res.getRepairer_id()));
			errorMsgs.setFacility_typeError(validateFacility_type(res.getFacility_type()));
			errorMsgs.setMar_idError(validateMar_id(res.getMar_id()));
			errorMsgs.setReservation_idError(validateReservation_id(res.getReservation_id()));

			errorMsgs.setErrorMsg("");
	}
	
	
	private String validateTime(Reservation res) {

	   	int k = dao.check(res);

		String result ="";
	       
       	if(k==0 )
       	{
       		result="";
       	}
       	else
       	{
       		result="Reservation Cannot be done already another reservation present for the same time!";
       	}
       	return result;
	}
	
	private String validateRepairer(String repairer_id)
	{
		String res = "";
		
		if(repairer_id.equalsIgnoreCase(""))
		{
			res="no Value";
		}
		return res;
	}
	
	private String validateMar_id(String repairer_id)
	{
		String res = "";
		
		if(repairer_id.equalsIgnoreCase(""))
		{
			res="no Value";
		}
		return res;
	}
	
	
	private String validateFacility_name(String repairer_id)
	{
		String res = "";
		
		if(repairer_id.equalsIgnoreCase(""))
		{
			res="no Value";
		}
		return res;
	}
	

	private String validateReservation_id(String repairer_id)
	{
		String res = "";
		
		if(repairer_id.equalsIgnoreCase(""))
		{
			res="no Value";
		}
		return res;
	}
	
	private String validateFacility_type(String repairer_id)
	{
		String res = "";
		
		if(repairer_id.equalsIgnoreCase(""))
		{
			res="no Value";
		}
		return res;
	}

	
}
