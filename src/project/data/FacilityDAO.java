package project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.model.Facility;
import project.model.MAR;
import project.model.Reservation;
import project.model.User;
import project.util.SQLConnection;



public class FacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	
	private static void StoreListinDB (Facility facility,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertNewFacility = queryString + " VALUES (NULL,'"  
					+ facility.getFacilityName()  + "','"
					+ facility.getFacilityType() + "','"		
					+ facility.getInterval() + "','"
					+ facility.getDuration() + "','"
					+ facility.getVenue() +"')";
			
			stmt.executeUpdate(insertNewFacility);	
			
			conn.commit(); 
		} catch (SQLException e) {}
	}

	
	private static void updateMar(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate(queryString);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	
	public static void insertNewFacility(Facility facility) {  
		StoreListinDB(facility,"INSERT INTO `mac_facility`.`facility` (`facility_id`, `facility_type`, `facility_name`, `interval`, `duration`, `venue`) ");
		
		

	} 
	
	private static ArrayList<MAR> ReturnMatchingMarList (String queryString) {
		ArrayList<MAR> marListInDB = new ArrayList<MAR>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				MAR mar = new MAR(); 
				mar.setMar_number((marList.getString("mar_number")));
				mar.setFacility_type(marList.getString("facility_type"));
				mar.setFacility_name(marList.getString("facility_name"));
				mar.setUrgency(marList.getString("urgency"));
				mar.setDesc(marList.getString("desc"));
				mar.setReported_by(marList.getString("reported_by"));
				mar.setDate(marList.getString("date"));
				mar.setAssigned_to(marList.getString("assigned_to"));
				mar.setAssigned_date(marList.getString("assigned_date"));
				mar.setEstimate_of_repair(marList.getString("estimate_of_repair"));
				marListInDB.add(mar);	
			}
		} catch (SQLException e) {}
		return marListInDB;
	}
	

	private static ArrayList<Facility> ReturnMatchingFacilitList(String queryString) {
		ArrayList<Facility> facilityListInDB = new ArrayList<Facility>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				Facility  facility = new Facility(); 
			
				facility.setFacilityType(facilityList.getString("facility_type"));
				facility.setFacilityName(facilityList.getString("facility_name"));
				facilityListInDB.add(facility);	
			}
		} catch (SQLException e) {}
		return facilityListInDB;
	}
	
	
	public static ArrayList<MAR>  getUnassignedMars() {  
		return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where `assigned_to` is null");
	}
	
	public static ArrayList<MAR>  getAssignedMars(String name) {
		int id = Integer.parseInt(name);
		return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where `assigned_to` = "+id+";");
	}
	
	
	public static ArrayList<MAR>  getMars() {  
		return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where `assigned_to` is not null ;");
	}

	public static ArrayList<MAR>  getMarsD(String hell) {  
		return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where assigned_date='"+hell+"';");
	}
	
	public static ArrayList<MAR>  getMarsU(String hell) {  
		return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where date='"+hell+"';");
	}
	
	public static ArrayList<MAR> searchMar(String id) {
		
		int din = Integer.parseInt(id);
		 return ReturnMatchingMarList(" SELECT * FROM mac_facility.mar where `mar_number` ="+din);
	}

	public static ArrayList<User> searchUser(String user) {
		
		return ReturnMatchingUserList(" SELECT * FROM mac_facility.user where `role` ='"+user+"'");
	}

	private static ArrayList<User> ReturnMatchingUserList(String queryString) {
	ArrayList<User> userListInDB = new ArrayList<User>();
		 
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				User user = new User(); 
				user.setUtaID(((userList.getString("uta_id"))));
				user.setLastName(userList.getString("lname"));

				userListInDB.add(user);	
			}
		} catch (SQLException e) {}
		return userListInDB;
	}

	public static int assignMore(String assigned_to, String assigned_date) {
		int count =0;
		String query=" SELECT count(*) FROM mac_facility.mar where `assigned_to` ="+assigned_to+" and `assigned_date` = '"+assigned_date+"';";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery(query);
			while(result.next())
			{
				count = result.getInt("count(*)");
			}
		
			conn.commit(); 
		} catch (SQLException e) {}
		return count;
	}

	

	public static void updateMar(MAR mar) {
	updateMar("UPDATE `mac_facility`.`mar` SET `facility_type`='"+mar.getFacility_type()+"',"
			+ " `facility_name`='"+mar.getFacility_name()+"',"
			+ " `urgency`='"+mar.getUrgency()+"',"
			+ " `desc`='"+mar.getDesc()+"',"
			+ " `assigned_to`="+mar.getAssigned_to()+","
			+ " `assigned_date`='"+mar.getAssigned_date()+"',"
			+ " `estimate_of_repair`='"+mar.getEstimate_of_repair()+"'"
			+ " WHERE `mar_number`="+mar.getMar_number()+";");

	}


	public static ArrayList<Facility> getFacilityBy(String fac) {
		
		return ReturnMatchingFacilitList("select "+fac+" from `mac_facility`.`facility`;");
		

	}


	public static int assignWeek(String assigned_to, String assigned_date) {
		int count =0;
		String query ="select count(*) from mar where `assigned_to` = "+assigned_to+" and week('"+assigned_date+"') = week('"+assigned_date+"');";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery(query);
			while(result.next())
			{
				count = result.getInt("count(*)");
			}
			System.out.println(query);
			System.out.println(count);
			conn.commit(); 
		} catch (SQLException e) {}
		return count;
	}
	public List<User> list() throws SQLException {
        List<User> listRepairer = new ArrayList<>();
        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT uta_id, fname, lname FROM user WHERE role='repairer'";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	User repairer = new User();
                repairer.setUtaID(result.getString("uta_id"));
                repairer.setFirstName(result.getString("fname"));
                repairer.setLastName(result.getString("lname"));
                     
                listRepairer.add(repairer);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return listRepairer;
    }
    public List<Reservation> getRepairerSchedule(String repairer_id) throws SQLException
    {
    	List<Reservation> repairerSchedule = new ArrayList<Reservation>();

        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT repairer_id, mar_id, facility_name,date,timeslot FROM reservation WHERE repairer_id='"+repairer_id+"'";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	Reservation reservation = new Reservation();
                reservation.setRepairer_id(result.getString("repairer_id"));
                reservation.setMar_id(result.getString("mar_id"));
                reservation.setFacility_name(result.getString("facility_name"));
                reservation.setDate(result.getString("date"));
                reservation.setTimeslot(result.getString("timeslot"));
               repairerSchedule.add(reservation);                
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } 
        return repairerSchedule;
    }
    
    public String getFacilityNumbers(String facility_type , String facility_name, String date, String timeslot) throws SQLException
    {
    	String result;
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
		 try  {
	        	String typeQuery = "SELECT count(*) AS Count FROM facility WHERE facility_type='"+facility_type+"'";
	        	//String nameQuery = "SELECT count(*) AS Count1 FROM facility WHERE facility_name='"+facility_name+"'";
	        	String venueQuery = "Select count(*) As count2 from `mac_facility`.`reservation` where date='"+date+"' and facility_type='"+facility_type+"'and timeslot ='"+timeslot+"'";
	        	
	        	stmt = conn.createStatement();
	        	//stmt1 = conn.createStatement();
	        	stmt2 = conn.createStatement();
	        	int x=0,y=0,z=0;
				ResultSet typeResult = stmt.executeQuery(typeQuery);
				
				while(typeResult.next())
				{
					x = typeResult.getInt("Count");
				}
				
				
				ResultSet venueResult = stmt2.executeQuery(venueQuery);
				while(venueResult.next())
				{
					 z = venueResult.getInt("Count2");
				}
				result = "Number of Facilities Avaliable of Type "+facility_type+" on "+date+" at "+timeslot+" : - "+(x-z)+"";
						
				
	             
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw ex;
	        } 
		 return result;
	        
    }
    public List<Facility> getTypeDropdown() throws SQLException
    {
    	List<Facility> typeDropdown = new ArrayList<Facility>();

        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT distinct facility_type FROM facility";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	Facility facility = new Facility();
            	facility.setFacilityType(result.getString("facility_type"));
                typeDropdown.add(facility) ;               
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } 
        return typeDropdown;
    }
    public List<Facility> getNameDropdown() throws SQLException
    {
    	List<Facility> nameDropdown = new ArrayList<Facility>();

        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT distinct facility_name FROM facility";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	Facility facility = new Facility();
            	facility.setFacilityName(result.getString("facility_name"));
                nameDropdown.add(facility) ;               
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } 
        return nameDropdown;
    }
    public List<Facility> getVenueDropdown() throws SQLException
    {
    	List<Facility> venueDropdown = new ArrayList<Facility>();

        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT distinct venue FROM facility";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	Facility facility = new Facility();
            	facility.setVenue(result.getString("venue"));
                venueDropdown.add(facility) ;                
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } 
        return venueDropdown;
    }
    public List<Facility> getFacilityDetails( String facility_name) throws SQLException
    {
    	List<Facility> facilityDetails = new ArrayList<Facility>();

        Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        try  {
        	String queryString = "SELECT * FROM facility WHERE facility_name='"+facility_name+"'";
        	stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
            while (result.next()) {
            	Facility facility = new Facility();
            	facility.setFacilityType(result.getString("facility_type"));
            	facility.setInterval(result.getString("interval"));
            	facility.setDuration(result.getString("duration"));
            	facility.setVenue(result.getString("venue"));
                facilityDetails.add(facility) ;                
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } 
        return facilityDetails;
    }
}
