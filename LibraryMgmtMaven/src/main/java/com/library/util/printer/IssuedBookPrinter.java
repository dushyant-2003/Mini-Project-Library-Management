package com.library.util.printer;
 
import java.util.List;

import com.library.Model.IssuedBookDetails;
 

public class IssuedBookPrinter {
 
//	String notificationId;
//	String userId;
//	String description;
//	String type;
//	LocalDate dateIssued;
 
	private static final String HEADER_FORMAT = "%-10s %-20s %-20s %-25s %-35s %-20s %-10s %-15s %-15s %-10s";
	private static final String ROW_FORMAT = "%-10d %-20s %-20s %-25s %-35s %-20s %-10.2f %-15s %-15s %-10.2f";
 
	private static final String BOX_BORDER = "=========================================================================================================================================================================================";
	private static final String DIVIDER_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
 
	// private static final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
	public static void printIssuedBooks(List<IssuedBookDetails> issuedBooks) {
		// Print table title
		System.out.println(BOX_BORDER);
		System.out.println(centerTextInBox("Issued Books"));
		System.out.println(BOX_BORDER);
 
		// Print header
		System.out.printf(HEADER_FORMAT, "S.No.", "Username", "Issuer Name", "Email","Book Name","Author","Price","Issue Date","Deadline","Fine");
		System.out.println();
		System.out.println(DIVIDER_LINE);
 
		// Print rows
		int index = 1;
		for (IssuedBookDetails book : issuedBooks) {
			try {
				System.out.printf(ROW_FORMAT, index++, book.getUserName(),book.getIssuerName(),book.getEmail(),book.getBookName(),book.getAuthor(),book.getPrice(),book.getIssueDate(),book.getDeadline(),book.getFine()
						);
				System.out.println();
				System.out.println(DIVIDER_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while printing books: ");
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
 
