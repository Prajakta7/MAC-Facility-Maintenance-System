package project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.model.Facility;
import project.model.MAR;
import project.util.SQLConnection;


public class MARDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();


	private static int StoreListinDB (MAR mar,String queryString) {
		int res = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		



		try {
			stmt = conn.createStatement();
			String insertNewMAR = queryString + " VALUES (NULL,'"  
					+ mar.getFacility_type()  + "','"
					+ mar.getFacility_name() + "','"		
					+ mar.getUrgency() + "','"
					+ mar.getDesc() + "','"
					+ mar.getReported_by() + "','" 
					+ mar.getDate() +"',NULL,NULL,NULL);";

		

			res=stmt.executeUpdate(insertNewMAR);	
			conn.commit(); 
		} catch (SQLException e) {}
		
		return res;
	}

	public static int insertNewMAR(MAR mar) {  
		int res = StoreListinDB(mar,"INSERT INTO `mac_facility`.`mar` (`mar_number`, `facility_type`, `facility_name`, `urgency`, `desc`, `reported_by`,`date`,`assigned_to`,`assigned_date`,`estimate_of_repair`) ");
		
		return res;

	}


	public List<Facility> list() throws SQLException {
		List<Facility> listFacility = new ArrayList<>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try  {
			String queryString = "SELECT facility_name, facility_type FROM facility";
		
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
			while (result.next()) {
				Facility facility = new Facility();
				facility.setFacilityName(result.getString("facility_name"));
				facility.setFacilityType(result.getString("facility_type"));
				

				listFacility.add(facility);
			}          

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		}      

		return listFacility;
	}
	
	public static String getUTAID(String username) {
		
		String utaid = null;
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try  {
			String query = "SELECT uta_id FROM user WHERE username = '" + username + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				utaid = rs.getString(1);
			}     

		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}      
		
		return utaid;
		
	}
}

