package com.jdbc.leanings.inputs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
	
	public static int getIntegerInput(String prompt) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(prompt);
		System.out.flush();
		
		try {
			return Integer.parseInt(stdin.readLine());
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static double getDoubleInput(String prompt) throws NumberFormatException{
		
		int input = getIntegerInput(prompt);
		return 0;
	}

}
