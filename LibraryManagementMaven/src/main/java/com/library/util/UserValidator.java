package com.library.util;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class UserValidator {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidGender(String gender) {

		List<String> genders = List.of("male", "female", "other");

		return genders.contains(gender.toLowerCase());

	}

	public static boolean isValidPhoneNo(String phoneNo) {
		if (phoneNo == null) {
			return false;
		}
		Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");
		Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNo);
		return matcher.matches();
	}

	public static boolean isValidUsername(String username) {
		if (username == null || username.length() < 8) {
			return false;
		}
		return !username.contains(" ");
	}

	public static boolean isValidAge(int age) {
		return age >= 0 && age < 100;
	}

	public static boolean isValidName(String name) {
		String namePattern = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$";
		return name != null && name.matches(namePattern);
	}

	public static boolean isValidRole(String role) {
		List<String> roles = List.of("staff", "issuer");

		return roles.contains(role.toLowerCase());
	}

	public static boolean isValidBookIssueLimit(String bookIssueLimit) {
		// TODO Auto-generated method stub
		List<String> limits = List.of("0", "1", "2", "3");

		return limits.contains(bookIssueLimit);
	}

	public static boolean isValidDate(String date) {
		// Define the date formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

		try {
			// Parse the date string to LocalDate
			LocalDate parsedDate = LocalDate.parse(date, formatter);

			// Get today's date
			LocalDate today = LocalDate.now();

			// Check if the parsed date is before today
			return parsedDate.isBefore(today);

		} catch (DateTimeParseException e) {
			// The date string was not in the correct format
			return false;
		}
	}

	public static boolean isValidFine(double fine) {
		// TODO Auto-generated method stub
		return fine >= 0;
	}

	public static boolean isValidIndex(int index, int limit) {
		if (index > 0 && index <= limit) {
			return true;
		}
		return false;
	}

	public static boolean isValidYesNo(String isBookLost) {
		// TODO Auto-generated method stub
		if(isBookLost.trim().equalsIgnoreCase("y") || isBookLost.trim().equalsIgnoreCase("n")) {
			return true;
		}
		return false;
	}
}
