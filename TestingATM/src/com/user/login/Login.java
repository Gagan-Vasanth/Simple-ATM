package com.user.login;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.db.DBType;
import com.connection.db.DBUtil;

public class Login {
	
	
	
	public static boolean verifyUser (String acc) throws SQLException {
		
		String SQL = "SELECT account_number,firstName,lastName,balance from users where account_number = ?";
		ResultSet rs = null;
		
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				) {
			stmt.setString(1, acc);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				StringBuffer sf = new StringBuffer();
				sf.append("Hello "+ rs.getString("firstName"));
				System.out.println(sf.toString());
				return true;
			}
			
			else {
				return false;
			}
		
		} finally {
			if(rs != null) rs.close();
		}
	}
}
