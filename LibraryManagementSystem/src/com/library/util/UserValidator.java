package com.library.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	        "[a-zA-Z0-9_+&*-]+)*@" +
	        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	        "A-Z]{2,7}$";
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
  
}
