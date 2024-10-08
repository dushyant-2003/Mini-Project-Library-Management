package com.library.util.printer;
 
import java.util.List;

import com.library.model.Notification;
 
public class NotificationPrinter {
 
//	String notificationId;
//	String userId;
//	String description;
//	String type;
//	LocalDate dateIssued;
 
	private static final String HEADER_FORMAT = "%5s | %-25s | %-50s | %-20s";
	private static final String ROW_FORMAT = "%5d | %-25s | %-50s | %-20s";
 
	private static final String BOX_BORDER = "================================================================================================================";
	private static final String DIVIDER_LINE = "----------------------------------------------------------------------------------------------------------------";
 
	public static void printNotifications(List<Notification> notifications) {
		// Print table title
		System.out.println(BOX_BORDER);
		System.out.println(CenterTextInBox.centerTextInBox(BOX_BORDER,"NOTIFICATIONS"));
		System.out.println(BOX_BORDER);
 
		// Print header
		System.out.printf(HEADER_FORMAT, "S.No.", "Title", "Message", "Received At");
		System.out.println();
		System.out.println(DIVIDER_LINE);
 
		// Print rows
		int index = 1;
		for (Notification notification : notifications) {
			try {
				System.out.printf(ROW_FORMAT, index++, notification.getTitle(), notification.getMessage(),
						notification.getDate());
				System.out.println();
				System.out.println(DIVIDER_LINE);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while printing notification: " + notification);
			}
		}
	}
 
}
 
