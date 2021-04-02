package com.db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.connection.db.DBType;
import com.connection.db.DBUtil;


public class Users {

	public static void displayAllRows() throws SQLException {
		String sql = "SELECT account_number, firstName, lastName, balance FROM users";
		
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) 
			{
				System.out.println("User Table: ");
				int n=0;
				while(rs.next()) {
					StringBuffer bf = new StringBuffer();
					bf.append(n+1 + ".  ");
					bf.append(rs.getInt("account_number") + " : ");
					bf.append(rs.getString("firstName") + " : ");
					bf.append(rs.getString("lastName") + " : ");
					bf.append(rs.getString("balance") );
					System.out.println(bf.toString());
				}
			
		}
		catch(Exception e) {
			System.err.println("Hi" +e.getMessage());
		}
	}
}
