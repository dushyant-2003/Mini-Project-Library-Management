package com.library.util.printer;

import java.util.List;

import com.library.Model.IssuedBookDetails;

public class IssuedBookPrinter {

	private static final String HEADER_FORMAT = "%-10s %-20s %-20s %-25s %-35s %-20s %-10s %-15s %-15s %-10s";
	private static final String ROW_FORMAT = "%-10d %-20s %-20s %-25s %-35s %-20s %-10.2f %-15s %-15s %-10.2f";

	private static final String BOX_BORDER = "=========================================================================================================================================================================================";
	private static final String DIVIDER_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	public static void printIssuedBooks(List<IssuedBookDetails> issuedBooks) {

		System.out.println(BOX_BORDER);
		System.out.println(CenterTextInBox.centerTextInBox(BOX_BORDER,"Issued Books"));
		System.out.println(BOX_BORDER);

		System.out.printf(HEADER_FORMAT, "S.No.", "Username", "Issuer Name", "Email", "Book Name", "Author", "Price",
				"Issue Date", "Deadline", "Fine");
		System.out.println();
		System.out.println(DIVIDER_LINE);

		int index = 1;
		for (IssuedBookDetails book : issuedBooks) {
			try {
				System.out.printf(ROW_FORMAT, index++, book.getUserName(), book.getIssuerName(), book.getEmail(),
						book.getBookName(), book.getAuthor(), book.getPrice(), book.getIssueDate(), book.getDeadline(),
						book.getFine());
				System.out.println();
				System.out.println(DIVIDER_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while printing books: ");
			}
		}
	}

}
