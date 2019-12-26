package project.model;

public class MARErrorMsgs {

	private String errorMsg="";
	private String assigned_DateError="";
	private String assigned_more="";
	private String desc="";

	private String assigned_week="";
	private String assigned_to="";
	private String estimate_of_repair="";
	private String repErr="";
	private String marNumErr="";
	private String facTypeErr="";
	public String getRepErr() {
		return repErr;
	}

	public void setRepErr(String repErr) {
		this.repErr = repErr;
	}

	public String getMarNumErr() {
		return marNumErr;
	}

	public void setMarNumErr(String marNumErr) {
		this.marNumErr = marNumErr;
	}

	public String getFacTypeErr() {
		return facTypeErr;
	}

	public void setFacTypeErr(String facTypeErr) {
		this.facTypeErr = facTypeErr;
	}

	public String getFacNamErr() {
		return facNamErr;
	}

	public void setFacNamErr(String facNamErr) {
		this.facNamErr = facNamErr;
	}

	public String getRepDateErr() {
		return repDateErr;
	}

	public void setRepDateErr(String repDateErr) {
		this.repDateErr = repDateErr;
	}

	public String getUrgErr() {
		return urgErr;
	}

	public void setUrgErr(String urgErr) {
		this.urgErr = urgErr;
	}

	private String facNamErr;
	private String repDateErr;
	private String urgErr;

	public String getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public String getEstimate_of_repair() {
		return estimate_of_repair;
	}

	public void setEstimate_of_repair(String estimate_of_repair) {
		this.estimate_of_repair = estimate_of_repair;
	}

	
	public String getAssigned_week() {
		return assigned_week;
	}
	public void setAssigned_week(String assigned_week) {
		this.assigned_week = assigned_week;
	}


	/*public MARErrorMsgs()
	{
		this.errorMsg="";
		this.assigned_DateError="";
		this.assigned_more="";
	
	}*/
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setErrorMsg() {
		if (!assigned_DateError.equals("") || !assigned_more.equals("") ||!(assigned_week.equals("")) || !(assigned_to.equals("")) 
				|| !(estimate_of_repair.equals("")) || !(desc.equals("")))
			this.errorMsg = "Please correct the following errors";
		else
			this.errorMsg="";
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}
	
	public void setAssigned_DateErrorr(String assigned_DateError)
	{
		this.assigned_DateError = assigned_DateError;
	}
	
	public String getAssigned_DateError()
	{
		return this.assigned_DateError;
	}

	public String getAssigned_more() {
		return assigned_more;
	}

	public void setAssigned_more(String assigned_more) {
		this.assigned_more = assigned_more;
	}


	
}
