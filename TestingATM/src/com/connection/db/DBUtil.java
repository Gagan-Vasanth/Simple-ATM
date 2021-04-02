package com.connection.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword";
	private static final String CONN_URL = "jdbc:mysql://localhost:3307/atmtest";
	
	public static Connection getConnection(DBType dbType) throws SQLException {
		
		if(dbType == DBType.MYSQL) {
			return DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
		}
		return null;
	}
	
	public static void processException(SQLException e) {
		System.err.println("Error Message: "+ e.getMessage());
		System.err.println("Error Code: "+ e.getErrorCode());
		System.err.println("SQL State: "+e.getSQLState());
		
	}

}
