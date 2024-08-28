package com.library.ui;

import java.util.Scanner;

import com.library.Controller.BookController;
import com.library.Controller.NotificationController;
import com.library.Controller.UserController;
import com.library.Model.User;

public class MenuUI {
	
	
	private UserController userController;
	private BookController bookController;
	private NotificationController notificationController;
    Scanner scanner = new Scanner(System.in);
	public MenuUI(UserController userController,BookController bookController,NotificationController notificationController) {
		this.userController = userController;
		this.bookController = bookController;
		this.notificationController = notificationController;
		
	}
	

	public void displayLoginMenu()
	{
		
		while(true) {
			
			System.out.println("-----------------------------------");
			System.out.println("\t\tLOGIN");
			System.out.println("-----------------------------------");
		    System.out.print("Enter username: ");
		    String username = scanner.nextLine();

		    System.out.print("Enter password: ");
		    String password = scanner.nextLine();
		    
	    	
			User user = userController.authenticateUser(username,password);
			
				
			if(user == null)
			{
				System.out.println("Invalid credentials");
			}
			else
			{
				System.out.println("User Authenticated Successfully");	
				displayMenu(user.getRole().toString(),user);
			}
			
			
			
		}
		
	    
	}
     void displayMenu(String role,User user) {
		
    	UserUI userUI = new UserUI(user,userController,bookController,notificationController);

    	switch(role) {
    	case "ADMIN":
    		userUI.displayAdminMenu();
    		break;
    	case "STAFF":
    		userUI.displayStaffMenu();
    		break;
    	case "ISSUER":
    		userUI.displayClientMenu();
    		break;
    	}
	}
	
       
 	public static void displayUpdateMenu() {
 		System.out.println("\nWhich field would you like to update?");
 		System.out.println("1. Name");
 		System.out.println("2. Email");
 		System.out.println("3. Username");
 		System.out.println("4. Password");
 		System.out.println("5. Age");
 		System.out.println("6. Gender");
 		System.out.println("7. Contact Number");
 		System.out.println("8. Address");
 		System.out.println("9. Role");
 		System.out.println("10. Fine");
 		System.out.println("11. Book Issue Limit");
 		System.out.println("12. Done");
 	}
	
}
