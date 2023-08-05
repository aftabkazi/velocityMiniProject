package com.connection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

	public static boolean isValidName(String firstName) {
		String regex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(firstName);
		return matcher.matches();
	}

	public static boolean isValidUserName(String userName) {
		String regex = "^[a-z]+(_[a-z]+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		return matcher.matches();
	}

	public static boolean isValidPassword(String password) {
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(.{5,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		String regex = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidMobileNumber(long mobileNumber) {

		String regex = "\\d{10}";
		 String mobileNumberStr = String.valueOf(mobileNumber);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileNumberStr);
		return matcher.matches();

	}

}
