package com.library.util.printer;



import java.util.List;


import com.library.Model.User;
 

public class UserPrinter {
 
//	String notificationId;
//	String userId;
//	String description;
//	String type;
//	LocalDate dateIssued;
 
	private static final String HEADER_FORMAT = "%-10s %-25s %-25s %-10s %-15s %-15s %-15s %-15s %-15s %-25s %-25s %-25s %-10s";
	private static final String ROW_FORMAT =    "%-10d %-25s %-20s %-10s %-15s %-15s %-15s %-15s %-15s %-25s %-25s %-25d %-10.2f";
 
	private static final String BOX_BORDER = "=====================================================================================================================================================================================================================================================";
	private static final String DIVIDER_LINE = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
 
	// private static final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
	public static void printUsers(List<User> userList) {
		// Print table title
		System.out.println(BOX_BORDER);
		System.out.println(centerTextInBox("Users"));
		System.out.println(BOX_BORDER);
 
		// Print header
		System.out.printf(HEADER_FORMAT, "S.No.", "Name", "Username", "Gender","Date Of Birth","Role","Department","Designation","Contact","Email","Address","Book Issue Limit","Fine");
		System.out.println();
		System.out.println(DIVIDER_LINE);
 
		// Print rows
		int index = 1;
		for (User user : userList) {
			try {
				System.out.printf(ROW_FORMAT, index++, user.getName(),user.getUserName(),user.getGender(),user.getDateOfBirth(),user.getRole(),user.getDepartment(),user.getDesignation(),user.getContactNumber(),user.getEmail(),user.getAddress(),user.getBookIssueLimit(),user.getFine()
						);
				System.out.println();
				System.out.println(DIVIDER_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while printing users: ");
			}
		}
	}
 
	// Helper method to center the text within a box
	private static String centerTextInBox(String text) {
		int boxWidth = BOX_BORDER.length();
		int textLength = text.length();
		int padding = (boxWidth - textLength) / 2;
 
		// Creating a line with centered text surrounded by spaces
		StringBuilder centeredText = new StringBuilder();
		centeredText.append(" ".repeat(padding));
		centeredText.append(text);
		centeredText.append(" ".repeat(padding));
 
		// Ensure the line is exactly as wide as the box, accounting for odd width
		while (centeredText.length() < boxWidth) {
			centeredText.append(" ");
		}
 
		return centeredText.toString();
	}
}
 


