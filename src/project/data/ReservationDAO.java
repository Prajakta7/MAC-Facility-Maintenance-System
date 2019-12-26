package project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.model.MAR;
import project.model.Reservation;
import project.util.SQLConnection;

public class ReservationDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	public void insertReservation(Reservation res) {
		
		StoreReservationDB(res,"INSERT INTO `mac_facility`.`reservation` (`reservation_id`, `repairer_id`, `mar_id`, `facility_name`, `date`, `timeslot`,`facility_type`) "); ;
		
		
		
	}

	public int check(Reservation res)
	{
		
		return reservationInDB(res,"Select count(*) As count from `mac_facility`.`reservation` where date='"+res.getDate()+"' and facility_name='"+res.getFacility_name()+"' and timeslot ='"+res.getTimeslot()+"'"); 
		
	}
	
	public String type(Reservation res)
	{
		
		return facType("Select  duration from `mac_facility`.`facility` where facility_name='"+res.getFacility_name()+"'"); 
		
	}
	
	
	private String facType(String string) {
		Statement stmt = null;
		String k ="";
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(string);
			while(marList.next())
			{
				k = marList.getString("duration");
			}
			if(k.equalsIgnoreCase("Same day"))
			{
				return "Same day";
			}
			else {
				return "7-Day";
			}
			}
		 catch (SQLException e) {}
		return "";
	}
	private int reservationInDB(Reservation res, String queryString)  {
		System.out.println(queryString);
		Statement stmt = null;
		int x=0;
		Connection conn = SQLConnection.getDBConnection();
		 try  {
	        	
	        	stmt = conn.createStatement();
	        	
	        
				ResultSet typeResult = stmt.executeQuery(queryString);
				
				while(typeResult.next())
				{
					x = typeResult.getInt("Count");
				}
				
				
				
				
	             
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            
	        } 
		 return x;
		
	}
	
	private void StoreReservationDB(Reservation res, String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertNewFacility = queryString + " VALUES (NULL,'"  
					+ res.getRepairer_id()  + "','"
					+ res.getMar_id() + "','"		
					+ res.getFacility_name() + "','"
					+ res.getDate() + "','"
					+ res.getTimeslot() +"','"
					+ res.getFacility_type()+"')";
		
			stmt.executeUpdate(insertNewFacility);	
			
			conn.commit(); 
		} catch (SQLException e) {}
		
	}

	public String checkReservation(Reservation res) {
		return check("select * from reservation where `mar_id` ="+ res.getMar_id());
		
	}

	private String check(String string) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(string);
			
			if(marList.next())
			{
				return "there";
			}
			else {
				return "notThere";
			}
			}
		 catch (SQLException e) {}
		return "";
	}

	public static ArrayList<Reservation> getAssignedreservations(String uta_id) {
		int id = Integer.parseInt(uta_id);
		return ReturnMatchingReservationList(" SELECT * FROM mac_facility.reservation where `repairer_id` = "+id+";");
	
	}

	private static ArrayList<Reservation> ReturnMatchingReservationList(String queryString) {
		ArrayList<Reservation> resListInDB = new ArrayList<Reservation>();
	
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				Reservation res = new Reservation(); 
				res.setMar_id((marList.getString("mar_id")));
				res.setFacility_name(marList.getString("facility_name"));
				res.setRepairer_id(marList.getString("repairer_id"));
				res.setDate(marList.getString("date"));
				res.setTimeslot(marList.getString("timeslot"));
				res.setReservation_id(marList.getString("reservation_id"));
				resListInDB.add(res);	
			}
		} catch (SQLException e) {}
		return resListInDB;
	}

	public void modifyReservation(Reservation res) {
		ModifyReservationDB("UPDATE `mac_facility`.`reservation` SET `timeslot`='"+res.getTimeslot()+"' WHERE `reservation_id`='"+res.getReservation_id()+"';");
		
	}
	
	

	private void ModifyReservationDB(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate(queryString);	
		
			conn.commit(); 
		} catch (SQLException e) {}
		
	}

	public void cancelReservation(Reservation res) {
		CancelReservationDB("DELETE FROM `mac_facility`.`reservation` WHERE `reservation_id`='"+res.getReservation_id()+"';");
	}

	private void CancelReservationDB(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			stmt.execute(queryString);	
		
			conn.commit(); 
		} catch (SQLException e) {}
		
	}

}
