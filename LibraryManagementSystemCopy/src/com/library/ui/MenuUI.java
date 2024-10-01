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
			System.out.println("Enter your role (Admin, Staff or Client): ");
		    String role = scanner.nextLine();

		    System.out.print("Enter username: ");
		    String username = scanner.nextLine();

		    System.out.print("Enter password: ");
		    String password = scanner.nextLine();
		    
		    
			boolean isAuthenticatedUser = userController.authenticateUser(username,password,role);	
				
			if(isAuthenticatedUser)
			{
				System.out.println("User Authenticated Successfully");
				User user = userController.getUserByUserName(username);
				displayMenu(role,user);
			}
			else
			{
				System.out.println("Invalid credentials");
			} 
		}
		
	    
	}
     void displayMenu(String role,User user) {
		
    	UserUI userUI = new UserUI(user,userController,bookController,notificationController);

		if (role.equals("Admin")) {
			
			userUI.displayAdminMenu();
			
		}
	
		else if (role.equals("Staff")) {
			
			userUI.displayStaffMenu();
			
		} 
		else if (role.equals("Client")) {
			
			userUI.displayClientMenu();
			
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
 		System.out.println("10. Done");
 	}
	
}
