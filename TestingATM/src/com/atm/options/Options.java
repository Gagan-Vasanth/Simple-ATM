package com.atm.options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.db.DBType;
import com.connection.db.DBUtil;

public class Options {
	
	
		
		public static boolean withdraw(String acc) throws SQLException, NumberFormatException, IOException {
			String SQL1 = "SELECT balance from users where account_number = ?";
			String SQL2 = "UPDATE users SET balance = ? WHERE account_number = ?";
			ResultSet rs = null;
			
			try (
					Connection conn = DBUtil.getConnection(DBType.MYSQL);
					PreparedStatement stmt = conn.prepareStatement(SQL1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					)
			{
				stmt.setString(1, acc);
				rs = stmt.executeQuery();
				rs.next();
				
				double available_balance = rs.getDouble("balance");
				double amount_to_withdrawn;
				
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				
				boolean loop = false;
				
				do {
					
					System.out.println("Please Enter the amount to withdraw: ");
					amount_to_withdrawn = Double.parseDouble(bf.readLine());
					
					if(amount_to_withdrawn > available_balance) {
						System.out.println("Please Enter a valid ");
						loop = true;
					}
					else {
						loop = false;
					}
					
				} while (loop);
				
				PreparedStatement stmt1 = conn.prepareStatement(SQL2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				double new_balance = available_balance - amount_to_withdrawn;
				stmt1.setDouble(1,new_balance);
				stmt1.setString(2, acc);
				
				int rs1 = stmt1.executeUpdate();
				
				if(rs1 == 1) {
					System.out.println("You have successfully withdrawn "+ amount_to_withdrawn+ " from your account");
					System.out.println("Now the Available balance is: " + new_balance);
					System.out.println("Thank you! Visit Again");
					System.exit(0);
				}
			} catch(SQLException e) {
				System.err.println("Error in withdrawn: "+ e.getMessage());
				System.exit(1);
			} finally {
				rs.close();
			}
			
			
			return true;
		}
		
		public static boolean deposit(String acc) throws NumberFormatException, IOException, SQLException {
			
			String SQL1 = "SELECT balance from users where account_number = ?";
			String SQL2 = "UPDATE users SET balance = ? WHERE account_number = ?";
			ResultSet rs = null;
			
			try (
					Connection conn = DBUtil.getConnection(DBType.MYSQL);
					PreparedStatement stmt = conn.prepareStatement(SQL1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					)
			{
				stmt.setString(1, acc);
				rs = stmt.executeQuery();
				rs.next();
				
				double available_balance = rs.getDouble("balance");
				double amount_to_deposit;
				
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				
				boolean loop = false;
				
				do {
					
					System.out.println("Please Enter the amount to withdraw: ");
					amount_to_deposit = Double.parseDouble(bf.readLine());
					
					if(amount_to_deposit <= 0) {
						System.out.println("Please Enter a valid Amount");
						loop = true;
					}
					else {
						loop = false;
					}
					
				} while (loop);
				
				PreparedStatement stmt1 = conn.prepareStatement(SQL2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				double new_balance = available_balance + amount_to_deposit;
				stmt1.setDouble(1,new_balance);
				stmt1.setString(2, acc);
				
				int rs1 = stmt1.executeUpdate();
				
				if(rs1 == 1) {
					System.out.println("You have successfully depositted "+ amount_to_deposit+ " into your account");
					System.out.println("Now the Available balance is: " + new_balance);
					System.out.println("Thank you! Visit Again");
					System.exit(0);
				}
			} catch(SQLException e) {
				System.err.println("Error in deposit: "+ e.getMessage());
				System.exit(1);
			} finally {
				rs.close();
			}
			return true;
		}
		
		public static boolean checkBalance(String acc) throws SQLException {
			
			String SQL1 = "SELECT balance from users where account_number = ?";
			ResultSet rs = null;
			
			try (
					Connection conn = DBUtil.getConnection(DBType.MYSQL);
					PreparedStatement stmt = conn.prepareStatement(SQL1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					)
			{
				stmt.setString(1, acc);
				rs = stmt.executeQuery();
				rs.next();
				
				double available_balance = rs.getDouble("balance");
				
				System.out.println("The Available balance is: " + available_balance);
				
				System.exit(0);
			} catch(SQLException e) {
				System.out.println("Error in Balance Check: " + e.getMessage());
			} finally {
				rs.close();
			}
			
			return true;
		}
		
		

}
