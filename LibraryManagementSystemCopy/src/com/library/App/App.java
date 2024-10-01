package com.library.App;
 
import java.sql.SQLException;

import com.library.Controller.BookController;
import com.library.Controller.NotificationController;
import com.library.Controller.UserController;
import com.library.DAO.BookDAO;
import com.library.DAO.NotificationDAO;
import com.library.DAO.UserDAO;
import com.library.Service.BookService;
import com.library.Service.NotificationService;
import com.library.Service.UserService;
import com.library.ui.MenuUI;

 

public class App {
 
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		UserDAO userDAO = new UserDAO();
		BookDAO bookDAO = new BookDAO();
		NotificationDAO notificationDAO = new NotificationDAO();
		
		UserService userService = new UserService(userDAO);
		BookService bookService = new BookService(bookDAO);
		NotificationService notificationService = new NotificationService(notificationDAO);
		UserController userController = new UserController(userService);
		BookController bookController = new BookController(bookService);
		NotificationController notificationController = new NotificationController(notificationService);
		MenuUI menuUI = new MenuUI(userController,bookController,notificationController);
		menuUI.displayLoginMenu();
		
	}
}
 
