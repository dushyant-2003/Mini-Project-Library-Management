package com.dushyant.junit;
//
//public class PasswordValidator {
//	public boolean isValidPassword(String password)
//	{
//		boolean checkUpperCase = false;
//		boolean checkSpecialCharacter = false;
//		boolean checkDigit = false;
//		
//		if(password.length() < 8)
//		{
//			return false;
//		}
//		for(char character: password.toCharArray())
//		{
//			if(Character.isUpperCase(character))
//			{
//				checkUpperCase = true;
//			}
//			if(Character.isDigit(character))
//			{
//				checkDigit = true;
//			}
//			if(password.contains("$") || password.contains("_") || password.contains("@")
//		|| password.contains("?"))
//			{
//				checkSpecialCharacter = true;
//			}
//			
//		}
//		
//		if(checkUpperCase == true && checkSpecialCharacter == true && checkDigit == true)
//		{
//			return true;
//		}
//				
//		
//		return false;
//		
//	}
//}


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public void displayPasswordRulesMenu() {
    	String textBlock = """
    			Password must be at least 8 characters long containing at least one digit, uppercase and special character. 
    			""";
    	System.out.println(textBlock);
    }
}
