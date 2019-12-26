package project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import project.data.FacilityDAO;

public class MAR {
	
	private String mar_number;
	private String facility_type;
	private String facility_name;
	private String urgency;
	private String desc;
	private String reported_by;
	private String date;
	private String assigned_to;
	private String assigned_date;
	private String estimate_of_repair;
	MARErrorMsgs errorMsgs;

	public String validatesUrgency(String urg)
	{
		String res="";
		if(urg.equals(""))
		{
			res="No value";
		}
		return res;
	}
	public String validatesMar_number(String marNum)
	{
		String res="";
		if(marNum.equalsIgnoreCase(""))
		{
			res="No value";
		}
		return res;
	}
	public String validatesFacility_type(String facType)
	{
		String res="";
		if(facType.equals(""))
		{
			res="No value";
		}
		return res;
	}
	public String validatesFacility_name(String facNam)
	{
		String res="";
		if(facNam.equals(""))
		{
			res="No value";
		}
		return res;
	}
	
	public String validateReportedBy(String reportedBy)
	{
		setReported_by("");
		String res="";
		if(reportedBy==null || reportedBy.equalsIgnoreCase(""))
		{
			res="No value";
		}
		return res;
	}
	public String validateRepDate(String repDate)
	{
		String res="";
		if(repDate==null || repDate.equalsIgnoreCase(""))
		{
			res="No value";
		}
		return res;
	}
	public void setMar(String mar_number2, String facility_type2, String facility_name2, String desc2, String date2,
			String reported_by2, String assigned_to2, String assigned_date2, String estimate_of_repair2, String urgency2) {
		setAssigned_date(assigned_date2);
		setAssigned_to(assigned_to2);
		setDate(date2);
		setDesc(desc2);
		setEstimate_of_repair(estimate_of_repair2);
		setFacility_name(facility_name2);
		setFacility_type(facility_type2);
		setMar_number(mar_number2);
		setUrgency(urgency2);
		setReported_by(reported_by2);
		
		
		
	}
	public String getMar_number() {
		return mar_number;
	}
	public void setMar_number(String mar_number) {
		this.mar_number = mar_number;
	}
	public String getFacility_type() {
		return facility_type;
	}
	public void setFacility_type(String facility_type) {
		this.facility_type = facility_type;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getReported_by() {
		return reported_by;
	}
	public void setReported_by(String reported_by) {
		this.reported_by = reported_by;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getAssigned_date() {
		return assigned_date;
	}
	public void setAssigned_date(String assigned_date) {
		this.assigned_date = assigned_date;
	}
	public String getEstimate_of_repair() {
		return estimate_of_repair;
	}
	public void setEstimate_of_repair(String estimate_of_repair) {
		this.estimate_of_repair = estimate_of_repair;
	}
	
	
	public String validateDescription(MAR mar, MARErrorMsgs errorMsgs) {
		String res = "";
		String desc = mar.getDesc();

		
		if (desc.equals("")) {
			res = "Description field cannot be empty";
		} else if (desc.length() < 3) {
			res = "Description cannot be less than 3 characters";

		}
		//errorMsgs.setDesc(res);
		return res;
	}

	
	public void validateMarAssignment (MAR mar, MARErrorMsgs errorMsgs) throws ParseException {
		//System.out.println("assigned date: "+mar.getAssigned_date()+" assigned to : "+ mar.getAssigned_to()+" date : "+ mar.getDate()+" desc: "+ mar.getDesc()+" estimate: "+ mar.getEstimate_of_repair()+" facility name: "+ mar.getFacility_name()+" facility type:"+
				//mar.getFacility_type()+" mar num: "+ mar.getMar_number()+" rep by: "+ mar.getReported_by());
		errorMsgs.setAssigned_DateErrorr(isValidDate(mar, errorMsgs));
		errorMsgs.setAssigned_more(validateAssignMore(mar.getAssigned_to(), mar.getAssigned_date()));
		errorMsgs.setAssigned_week(validateAssignWeek(mar.getAssigned_to(), mar.getAssigned_date()));
		errorMsgs.setDesc(validateDescription(mar, errorMsgs));
		errorMsgs.setAssigned_to(validateAssignedTo(mar.getAssigned_to()));
		errorMsgs.setEstimate_of_repair(validateEstimate(mar.getEstimate_of_repair()));
		errorMsgs.setErrorMsg();
		errorMsgs.setMarNumErr(validatesMar_number(mar.getMar_number()));
		errorMsgs.setFacTypeErr(validatesFacility_type(mar.getFacility_type()));
		errorMsgs.setFacNamErr(validatesFacility_name(mar.getFacility_name()));
		errorMsgs.setRepDateErr(validateRepDate(mar.getDate()));
		errorMsgs.setRepErr(validateReportedBy(mar.getReported_by()));
		errorMsgs.setUrgErr(validatesUrgency(mar.getUrgency()));
	
	}
	private String validateEstimate(String estimate_of_repair2) {
		String result="";
		if(estimate_of_repair2.equalsIgnoreCase(""))
		return "Please select Estimate Repair from drop down";
		
		return result;
	}

	private String validateAssignedTo(String assigned_to2) {
		String result="";
		if(assigned_to2.equalsIgnoreCase(""))
		return "Please select Assigned to field from drop down";
		
		return result;
	}


	
	public String validateAssignMore(String a1, String a2) {
		int count=0;
		
				count = FacilityDAO.assignMore(a1, a2);
				if(count>=5)
					return "Cant assign more then 5 mar to one repairer on single day";
					else
					return "";
		
	}
	
	public String validateAssignWeek(String a1, String a2)
	{
		int count =0;
		
		count = FacilityDAO.assignWeek(a1,a2);
		if(count>=10)
			return "Cant assign more then 10 mar to one repairer in a week";
			else
			return "";


	}

	public String isValidDate(MAR mar, MARErrorMsgs err) throws ParseException {
		String input = mar.getAssigned_date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String res ="";
	     try {
	          sdf.parse(input);
	          
	     }
	     catch(ParseException e){
	          res =  "Date should be in YYYY-mm-DD format";
	     }
		
		if(res.equals(""))
		{	
		  
	       Date date2 = null;
	       Date date1 = new Date();
	       Date date4 = null;
	       String date3 = sdf.format(date1);
	       sdf.format(date1);
		
	       
	        	
	        	date4 = sdf.parse(date3);
				 date2 = sdf.parse(input);

	        
	        
	        if ((date4.after(date2))) {
	            res =res+ "Date can not earlier than todays date";
	        }
		}
		else
			res="Date should be in YYYY-mm-DD format";
		

		return res;
		
	
	}

}

