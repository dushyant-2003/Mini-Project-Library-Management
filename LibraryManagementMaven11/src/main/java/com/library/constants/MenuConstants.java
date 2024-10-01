package com.library.constants;

public class MenuConstants {
	public static final String ADMIN_MENU = """
			-------------------------------
				Admin Menu
			-------------------------------
			1. Add New User
			2. Fetch User Details by username
			3. Fetch all users
			4. Delete User
			5. Update User Details
			6. Add New Book
			7. Issue Book
			8. Return Book
			9. Fetch details of issued books
			10. Send Notification
			11. Delete Notification
			12. Logout
			13. Exit
						""";
	public static final String STAFF_MENU = """
			-------------------------------
				Staff Menu
			-------------------------------
			1. Fetch User Details by username
			2. Add book
			3. Issue Book
			4. Return Book
			5. Fetch details of issued books
			6. Logout
			7. Exit
						""";
	public static final String CLIENT_MENU = """
			-------------------------------
				Client Menu
			-------------------------------
			1. See notifications
			2. See issued books
			3. Pay fine
			4. Logout
			5. Exit
						""";
	public static final String UPDATE_MENU = """
			Which field would you like to update
			1. Name
			2. Email
			3. Username
			4. Password
			5. Department
			6. Designation
			7. Contact Number
			8. Address 
			9. Role
			10. Fine
			11. BookIssueLimit
			12. Gender
			13. Back to main menu
			""";
	
}
