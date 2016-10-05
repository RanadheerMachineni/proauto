package com.esteeminfo.prouto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "proauto_db";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "proauto";
		String password = "proauto";
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
