package com.esteeminfo.prouto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CommonDAO extends BaseDAO {

	public static String authenticateUser(String uname, String pwd)
			throws SQLException {

		String retString = null;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='"+uname+"' and password='"+pwd+"'");
		while (res.next()) {
			String role = res.getString("role");
			retString = role;
		}
		conn.close();
		return retString;
	}

	public static boolean registerEmployee(String empName, String empPassword, String empEmail, String empRole) {
		Connection conn;
		boolean registered = false;
		try {
			
			if(userExist(empName)) return registered;
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());

			conn = getConnection();
			String query= "INSERT INTO EMPLOYEE(employee_name,password,role,create_date) values(?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, empName);
	        preparedStmt.setString(2, empPassword);
	        preparedStmt.setString(3, empRole);
	        preparedStmt.setDate(4, currentTime	);
	      
	        int count = preparedStmt.executeUpdate();
	        registered = (count > 0);
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registered;
	}
	
	public static boolean userExist(String uname)
			throws SQLException {

		boolean userExist = false;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE WHERE employee_name='"+uname+"'");
		while (res.next()) {
			userExist = true;
		}
		conn.close();
		return userExist;
	}

	public static boolean vendorExist(String vName)
			throws SQLException {

		boolean vendorExist = false;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM vendor WHERE vendor_name='"+vName+"'");
		while (res.next()) {
			vendorExist = true;
		}
		conn.close();
		return vendorExist;
	}
	
	public static boolean registerVendor(String vName, String vAddress, String vEmail, String vFirstContact,
			String vSecondContact, String vThirdContact) {
		Connection conn;
		boolean registered = false;
		try {
			
			if(vendorExist(vName)) return registered;
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentTime = new java.sql.Date(calendar.getTime().getTime());

			conn = getConnection();
			String query= "INSERT INTO VENDOR(vendor_name,address,vendor_email,vendor_first_contact,vendor_second_contact,vendor_third_contact,create_date) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, vName);
	        preparedStmt.setString(2, vAddress);
	        preparedStmt.setString(3, vEmail);
	        preparedStmt.setString(4, vFirstContact);
	        preparedStmt.setString(5, vSecondContact);
	        preparedStmt.setString(6, vThirdContact);
	        preparedStmt.setDate(7, currentTime	);
	        int count = preparedStmt.executeUpdate();
	        registered = (count > 0);
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registered;
	}
}
