package org.gymadviser.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.gymAdviser.dto.Admin;

import java.sql.*;

public class LoginService extends CommonService {

	// Database credentials
	static String USER = "";
	static String PASS = "";
	static String DATABASE = "";
	HashMap<String, String> users = new HashMap<String, String>();

	public LoginService() {
		users.put("postgres", "postgres default admin");
		users.put("dshevchyk", "Dima Shevchyk");
		users.put("dimahum", "Dima Hum");
		users.put("mih403", "Misha Fartuh");
	}

	private boolean passwordCheck(String password) {
		return password != null && password != "" && password.trim() != ""
				&& password.length() == password.trim().length();
	}

	private boolean loginCheck(String userId) {
		return users.get(userId) != null;
	}

	public boolean authentificate(String database, String userId,
			String password) {
		DATABASE = database;
		USER = userId;
		PASS = password;
		if (!passwordCheck(password) || !loginCheck(userId)) {

			System.out.println("fail check");
			System.out.println("psw = " + password);
			System.out.println("user = " + userId);
			return false;
		}

		boolean result = false;
		Connection conn = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + DATABASE, USER, PASS);

			result = true;
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		return result;
	}

	public Admin getAdminDetales(String userId) {
		Admin admin = new Admin();
		admin.setAdminName(users.get(userId));
		admin.setAdminId(userId);
		return admin;
	}
	
	public static void main(String[] args){
		System.out.println("Connection to DB");
		Connection conn = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + "gymadviser_db", "postgres", "samsung1");
			
			ArrayList<String> tNames = new ArrayList();
			{
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("select  table_schema, table_name from information_schema.tables");
				while (rs.next()) {
					if (rs.getString("table_schema").equals("public")) {
						String tName = rs.getString("table_name");
						System.out.println(rs.getString("table_name"));
						tNames.add(tName);
	//					tables.add(rs.getString("table_name"));
					}
				}
			}
			
			for(String tName : tNames)
			{
				System.out.println("[" + tName + "]");
				if(tName.equals("permissions")){
//					Statement s1 = conn.createStatement();
//					s1.executeUpdate("INSERT INTO permissions (permission) VALUES ('Русские')");
				}
				
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM " + tName) ;
				while (rs.next()) {
					System.out.println(rs.getString(1));
	
				}
			}
//			result = true;
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

	}

}
