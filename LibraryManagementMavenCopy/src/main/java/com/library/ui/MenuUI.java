package com.library.ui;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.library.Constants.StringConstants;
import com.library.Controller.BookController;
import com.library.Controller.NotificationController;
import com.library.Controller.UserController;
import com.library.Model.User;
import com.library.util.LoggingUtil;

public class MenuUI {

	private static final Logger logger = LoggingUtil.getLogger(MenuUI.class);

	Scanner scanner = new Scanner(System.in);

	UserUI userUI = new UserUI();

	public MenuUI() {
	}

	public void displayLoginMenu() {

		while (true) {
			System.out.println(StringConstants.WELCOME);
			System.out.println(StringConstants.LOGIN);
			System.out.println(StringConstants.INPUT_USERNAME);

			String username = scanner.next();

//			Console console = System.console();  
//	        if(console == null) {
//	        	System.out.println(StringConstants.NULL);
//	        	
//	        }
//	        char[] input = console.readPassword(StringConstants.INPUT_PASSWORD);
//	        String password = new String(input);
//	        
			System.out.println(StringConstants.INPUT_PASSWORD);
			String password = scanner.next();

			User user = userUI.authenticateUser(username, password);
			if (user == null) {
				System.out.println(StringConstants.INVALID_CREDENTIALS);
				logger.info("Authentication failure of user " + username);
			} else {
				System.out.println(StringConstants.AUTHENTICATE_SUCCESS_MSG);
				logger.info("User " + username + " authenticated successfully");
				displayMenu(user.getRole().toString(), user);
			}
		}
	}

	void displayMenu(String role, User user) {

		switch (role) {
		case StringConstants.ADMIN_ROLE:
			userUI.displayAdminMenu(user);
			break;
		case StringConstants.STAFF_ROLE:
			userUI.displayStaffMenu(user);
			break;
		case StringConstants.ISSUER_ROLE:
			userUI.displayClientMenu(user);
			break;
		}
	}

}
