package com.esteeminfo.prouto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmployeeDAO extends BaseDAO {

	public static String authenticateUser(String uname, String pwd)
			throws SQLException {

		String retString = null;
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM EMPLOYEE");
		while (res.next()) {
			int id = res.getInt("employee_id");
			String empName = res.getString("employee_name");
			String role = res.getString("role");
			retString = role;
		}
		conn.close();
		return retString;
	}
}
