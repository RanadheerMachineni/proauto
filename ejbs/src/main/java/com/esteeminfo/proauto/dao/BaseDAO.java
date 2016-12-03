package com.esteeminfo.proauto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/proauto_db?autoReconnect=true&useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "proauto";
		String password = "proauto";
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
