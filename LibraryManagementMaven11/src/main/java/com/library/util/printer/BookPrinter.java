package com.library.util.printer;

import java.util.List;

import com.library.model.Book;
 

public class BookPrinter {
 
	private static final String HEADER_FORMAT = "%-10s %-25s %-20s %-25s %-5s %-10s %-10s %-10s";
	private static final String ROW_FORMAT = "%-10d %-25s %-20s %-25s %-5s %-10.2f %-10s %-10s";
 
	private static final String BOX_BORDER = "==============================================================================================================================================================================";
	private static final String DIVIDER_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
 
	public static void printBooks(List<Book> bookList) {
		// Print table title
		System.out.println(BOX_BORDER);
		System.out.println(CenterTextInBox.centerTextInBox(BOX_BORDER,"Available Books"));
		System.out.println(BOX_BORDER);
 
		// Print header
		System.out.printf(HEADER_FORMAT, "S.No.", "Name", "Author", "Publication","Edition","Price","Shelf","Status");
		System.out.println();
		System.out.println(DIVIDER_LINE);
 
		// Print rows
		int index = 1;
		for (Book book : bookList) {
			try {
				System.out.printf(ROW_FORMAT, index++, book.getName(),book.getAuthor(),book.getPublication(),book.getEdition(),book.getPrice(),book.getShelfLocation(),book.getStatus()
						);
				System.out.println();
				System.out.println(DIVIDER_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while printing books: ");
			}
		}
	}
 
	
}
 

