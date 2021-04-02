package com.user.signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.connection.db.DBType;
import com.connection.db.DBUtil;


public class Signup {
	
	public static boolean newUserSignin(String firstName, String lastName, int pin) throws SQLException {
		
		final double basicDeposit = 2000;
		
		
		String SQL = "INSERT into users (firstName,lastName,pin,balance,account_number)" + 
					 "VALUES (?,?,?,?,?)";
		
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				){
			stmt.setString(1,firstName);
			stmt.setString(2,lastName);
			stmt.setInt(3, pin);
			stmt.setDouble(4, basicDeposit);
			
			Random rnd = new Random();
			String account_number;
			
			int len = 6;
			boolean nonUnique = false;
			
			String search_acc = "SELECT account_number FROM users where account_number = ?";
			
			do {
				System.out.println("Entered do loop");
				account_number = "";
				for (int c = 0; c < len; c++) {
					account_number += ((Integer)rnd.nextInt(10)).toString();
				}
				
				ResultSet rs1 = null;
				try (
						Connection conn1 = DBUtil.getConnection(DBType.MYSQL);
						PreparedStatement stmt1 = conn1.prepareStatement(search_acc, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						){ 
					stmt1.setString(1,account_number);
					rs1 = stmt1.executeQuery();
					
					if(!rs1.next()) {
						break;
					}
					
					else {
						nonUnique = true;
						continue;
					}
					
				} 
				
				catch (SQLException e) {
					System.err.println(" Error in Getting account_number from Database :"  + e.getMessage());
				} 
				
				finally {
					if (rs1 != null) rs1.close();
				}
				
			} while (nonUnique);
			
			stmt.setString(5, account_number);
			
			int rs = stmt.executeUpdate();
			
			if(rs == 1) {
				System.out.println("Thank you for signIn \n Please note down your account_number and pin for future logins \n Your account Number is :" + 									account_number);
			}
			else {
				System.out.println("Sorry Error in processing your details");
			}
	
			return true;
		} 
		
		catch (SQLException e) {
			System.out.println("Error in Signin:" + e.getMessage());
			return false;
		} 
		
		
	}

}
