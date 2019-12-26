package project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.model.User;
import project.util.SQLConnection;

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static void insertUser(User register) {
		Statement stmt = null;
		Connection conn = DBMgr.getDBConnection();
		int utaID = Integer.parseInt(register.getUtaID());
		String insertUserQuery = "INSERT INTO user (uta_id,username,password,lname,fname,role,mobile,address,security_ans) VALUES('"
				+ utaID + "'," + "'" + register.getUserName() + "','" + register.getPassword() + "'," + "'"
				+ register.getLastName() + "'," + "'" + register.getFirstName() + "'," + "'" + register.getRoleType()
				+ "'," + "'" + register.getMobile() + "'," + "'" + register.getAddress() + "'," + "'"
				+ register.getSecurityans() + "')";
		
		try {

			conn = SQLConnection.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(insertUserQuery);
			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;

		}
	}

	public static String getRoleTypeFromDb(String un) {
		Statement stmt = null;
		Connection conn = null;
		String roleType = "";
		// int utaid = Integer.parseInt(utaID);
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			String query = "SELECT role FROM user WHERE username = '" + un + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				roleType = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleType;
	}
	
	public static String getIDFromDb(String un) {
		Statement stmt = null;
		Connection conn = null;
		String roleType = "";
		// int utaid = Integer.parseInt(utaID);
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			String query = "SELECT uta_id FROM user WHERE username = '" + un + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				roleType = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleType;
	}
	

	public static boolean validateLoginCredentials(String un, String pass) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + un + "' AND password = '" + pass + "'";
			ResultSet rs = stmt.executeQuery(query);
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;
		return false;
	}

	public static boolean validateForPasswordReset(String utaID, String securityans) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			int uta = Integer.parseInt(utaID);
			String query = "SELECT * FROM user WHERE uta_id = '" + uta + "' AND security_ans = '" + securityans + "'";
			ResultSet rs = stmt.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;
		return false;
	}

	public static void resetPassword(User resPass) {
		Statement stmt = null;
		Connection conn = DBMgr.getDBConnection();
		int utaID = Integer.parseInt(resPass.getUtaID());

		String updatePassQuery = "UPDATE user set password = '" + resPass.getPassword() + "' WHERE uta_id = '" + utaID
				+ "'";
		
		//System.out.println(updatePassQuery);
		System.out.println(updatePassQuery);
		try {

			conn = SQLConnection.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(updatePassQuery);
			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;

		}

	}

	public static boolean uniqueUserName(String un) {

		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + un + "'";
			ResultSet rs = stmt.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;
		return true;
	}
	
	public List<User> list(String username) throws SQLException {
		List<User> listUser = new ArrayList<>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try  {
			String queryString = "SELECT * FROM user WHERE username = '" + username + "' ";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
			while (result.next()) {
				User user = new User();
				user.setUtaID(result.getString("uta_id"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("fname"));
				user.setLastName(result.getString("lname"));
				user.setAddress(result.getString("address"));
				user.setRoleType(result.getString("role"));
				user.setUserName(result.getString("username"));

				

				listUser.add(user);
			}          

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		}      

		return listUser;
	}

	public static void updateProfile (String password, String fname, String lname, String address, String username) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try  {
			String query = "update user set password='"+ password +"',fname='"+ fname +"',lname='"+ lname +"', address='"+ address +"' where username='"+username+"'" ;
			stmt = conn.createStatement();
		

			stmt.executeUpdate(query);
			conn.commit(); 

		} catch (SQLException ex) {
			ex.printStackTrace();

		}      

	}
	
	public static void modifyProfile (String password, String fname, String lname, String address, String username, String role) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try  {
			String query = "update user set role='"+role+"', password='"+ password +"',fname='"+ fname +"',lname='"+ lname +"', address='"+ address +"' where username='"+username+"'" ;
			stmt = conn.createStatement();
		

			stmt.executeUpdate(query);
			conn.commit(); 

		} catch (SQLException ex) {
			ex.printStackTrace();

		}      

	}
	
	public static ArrayList<User> getUserRoleUsername(String type) {
		
		
		 return ReturnMatchingUtaidList(" SELECT * FROM mac_facility.user where username='"+type+"' ;");
	}
	
	public static ArrayList<User> getUserRoleType(String type) {
		
		
		 return ReturnMatchingUtaidList(" SELECT * FROM mac_facility.user where role='"+type+"' ;");
	}
	
	
	public static ArrayList<User> getUserRole() {
		
		
		 return ReturnMatchingUtaidList(" SELECT * FROM mac_facility.user ;");
	}
	private static ArrayList<User> ReturnMatchingUtaidList(String queryString) {
		
		ArrayList<User> admListInDB = new ArrayList<User>();
			 
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			try {
				stmt = conn.createStatement();
				ResultSet userList = stmt.executeQuery(queryString);
				while (userList.next()) {
					User user = new User(); 
					user.setUtaID(((userList.getString("uta_id"))));
					user.setRoleType(userList.getString("role"));
					user.setFirstName(userList.getString("fname"));
					user.setLastName(userList.getString("lname"));
					user.setMobile(userList.getString("mobile"));
					user.setAddress(userList.getString("address"));
					user.setUserName(userList.getString("username"));
					admListInDB.add(user);	
				}
			} catch (SQLException e) {}
			return admListInDB;
		}
	
	public static void updateRole(User user) {
	updateRole("UPDATE `mac_facility`.`user` SET `role`='"+user.getRoleType()+"' where `uta_id`="+user.getUtaID()+";"
			);
	
	}
	private static void updateRole(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate(queryString);	
			conn.commit(); 
		} catch (SQLException e) {}
	}

	public static int getID(String string) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
