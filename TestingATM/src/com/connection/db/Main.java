package com.connection.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.atm.options.Options;
import com.user.login.Login;
import com.user.signup.Signup;



public class Main {

	
	
	public static void main(String[] args) throws SQLException, IOException {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				) {
		
		} catch (SQLException e) {
			DBUtil.processException(e);
		}
		
		System.out.println("Hello Welcome to the NG ATM ");
		System.out.println("Please Choose from the Below List \n 1. Login 2. Signin");
		
		Scanner scon = new Scanner(System.in);
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = scon.nextInt();
		
		switch (n) 
		{
			case 1:
			{
				System.out.println("Please Enter your Account Number: \n ");
				String accnumber = bf.readLine();
				boolean verified = Login.verifyUser(accnumber);
				if(verified) {
					System.out.println("Successfully logged in");
					System.out.println("Please Select from the below list");
					System.out.println("1. Withdraw \n 2. Deposit \n 3. Balance 4. Exit");
					int l = scon.nextInt();
					switch (l) {
					case 1:
					{
						Options.withdraw(accnumber);
						break;
					}
					case 2:
					{
						Options.deposit(accnumber);
						break;
					}
					case 3:
					{
						Options.checkBalance(accnumber);
						break;
					}
					case 4:
					{
						System.out.println("Successfully terminated!");
						System.exit(0);
						break;
					}
					default:
						break;
					}
					
					
				}
				break;
			}
			
			case 2:
			{
				
				System.out.println("Please Enter the below details in order to signin:");
				
				System.out.println("Enter your first Name: ");
				
				String firstName = bf.readLine();
				
				System.out.println("Enter your second Name: ");
				
				String lastName = bf.readLine();
				
				System.out.println("Enter the pin for your account: ");
				
				int pin = Integer.parseInt(bf.readLine());
				
				Signup.newUserSignin(firstName, lastName, pin);
				
				break;
			}
			default:
				break;
		}
		scon.close();
		
	}
}
