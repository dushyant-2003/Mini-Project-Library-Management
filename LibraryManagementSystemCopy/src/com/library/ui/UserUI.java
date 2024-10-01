package com.library.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.library.Controller.BookController;
import com.library.Controller.NotificationController;
import com.library.Controller.UserController;
import com.library.Model.Book;
import com.library.Model.Gender;
import com.library.Model.Notification;
import com.library.Model.Role;
import com.library.Model.Status;
import com.library.Model.User;

public class UserUI {
	
	
	private UserController userController;
	private BookController bookController;
	private NotificationController notificationController;
	private User user;
    Scanner scanner = new Scanner(System.in);
	public UserUI(User user,UserController userController,BookController bookController,NotificationController notificationController) {
		this.userController = userController;
		this.bookController = bookController;
		this.notificationController = notificationController;
		this.user = user;
	}
	
	 public UserUI() {
		// TODO Auto-generated constructor stub
	}

	public void displayClientMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. See notifications");
		System.out.println("2. See issued books");
		System.out.println("3. Pay fine");
		System.out.println("4. Logout");
		System.out.println("5. Exit");
		
		while(true) {
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				readNotifications();
				break;
			case 2:
			
			case 3:
				
			case 4:
				System.out.println("Logout successful");
				return;
			case 5:
				System.out.println("Thanks...");
				System.exit(0);
			}
		}
	}



	 

	

	public void displayStaffMenu() {
		
		
		System.out.println("1. Fetch User Details by ID");
		System.out.println("2. Add New Book");
		System.out.println("3. Issue Book");
		System.out.println("4. Return Book");
		System.out.println("5. Get All Issued Books");
		System.out.println("6. See Defaulter list");
		System.out.println("7. Logout");
		System.out.println("8. Exit");
		
		while(true) {
			System.out.println("Enter your choice: ");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				getUserById();
				break;
			case 2:
				addBook();
				break;
			case 3:
				issueBook();
				break;
			case 4:
				
			case 7:
				System.out.println("Logout success");
				return;
			case 8:
				System.out.println("Exiting...");
				System.exit(0);
				
				
			}
		}
		
		
		
		
		
	}

	 public void displayAdminMenu() {
		
		System.out.println("1. Add New User");
		System.out.println("2. Fetch User Details by ID");
		System.out.println("3. Fetch all users");
		System.out.println("4. Delete User");
		System.out.println("5. Update User Details");
		System.out.println("6. Add New Book");
		System.out.println("7. Issue Book");
		System.out.println("8. Return Book");
		System.out.println("9. Get All Issued Books");
		System.out.println("10. See Defaulter list");
		System.out.println("11. Send Notification");
		System.out.println("12. Delete Notification");
		System.out.println("13. Logout");
		System.out.println("14. Exit");
		
		while(true)
		{
			System.out.println("Enter your choice: ");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				addUser();
				break;
			case 2:
				getUserById();
				break;
			case 3:
				getAllUsers();
				break;
			case 4:
				deleteUser();
				break;
			case 5:
				updateUser();
				break;
			case 6:
				addBook();
				break;
			case 7:
				issueBook();
				break;
			case 8:
				returnBook();
				break;
			case 9:
				
			case 10:
			
			case 11:
				sendNotification();
				break;
			case 12:
				deleteNotification();
				break;
			case 13:
				System.out.println("Logout success");
				return;
			case 14:
				System.exit(0);
			default:
				System.out.println("Enter valid choice");
			}
		}
		
	}	

	private void returnBook() {
		System.out.println("Enter book id: ");
		String bookId = scanner.next();
		System.out.println("Enter user id: ");
		String userId = scanner.next();
		bookController.returnBook(bookId,userId);
	}

	private void issueBook() {
		// TODO Auto-generated method stub
		System.out.println("Enter user id: ");
		String userId = scanner.next();
		System.out.println("Enter book id: ");
		String bookId = scanner.next();
		boolean status = bookController.issueBook(bookId,userId);
		if(status) {
			System.out.println("Success");
		}
		else {
			System.out.println("Failure");
		}
	}

	private void deleteUser() {
		// TODO Auto-generated method stub
		System.out.println("Demo testing " + user);
		System.out.println("Enter userId: ");
		String userId = scanner.next();
		boolean status = userController.deleteUser(userId);
		
		if(status)
		{
			System.out.println("User deleted succeessfully");
		}
		else
		{
			System.out.println("Unsuccessful");
		}
		
	}

	private void getAllUsers() {
		// TODO Auto-generated method stub
		
			List<User> list = userController.getAllUsers();
			for (User user : list) {
				System.out.println(user);
			}
		
	}

	private void getUserById() {
		System.out.println("Enter user id");
		String userId = scanner.next();
		
		User user = userController.getUserByUserName(userId);
		if(user == null) {
			System.out.println("User not found");
		}
		else {
			System.out.println(user);
		}
		
	}

	private void addUser() {
		// TODO Auto-generated method stub
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 7;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String userId = randomString.substring(0, desiredLength);
		userId = 'U' + userId;
		scanner.nextLine();
		System.out.println("Enter Name:");
        String name = scanner.nextLine();
      
        System.out.println("Enter Username:");
        String userName = scanner.nextLine();

        System.out.println("Enter Role:");
        String role = scanner.nextLine(); 
        
        System.out.println("Enter Gender:");
        String gender = scanner.nextLine(); // 
        
        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter Department:");
        String department = scanner.nextLine();

        System.out.println("Enter Designation:");
        String designation = scanner.nextLine();

        System.out.println("Enter Contact Number:");
        String contactNumber = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Address:");
        String address = scanner.nextLine();

        System.out.println("Enter Book Issue Limit:");
        int bookIssueLimit = Integer.parseInt(scanner.nextLine());

        BigDecimal fine = new BigDecimal("0.0");

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User user = new User(userId, name, userName, Role.valueOf(role), Gender.valueOf(gender), dateOfBirth,
                department, designation, contactNumber, email, address, bookIssueLimit, fine, password);

        userController.addUser(user);
		 
	}
	
	private void addBook() {
		System.out.println("Enter book title:");
        String bookTitle = scanner.nextLine();

        System.out.println("Enter author:");
        String author = scanner.nextLine();

        System.out.println("Enter publication:");
        String publication = scanner.nextLine();

        System.out.println("Enter edition:");
        String edition = scanner.nextLine();

        System.out.println("Enter price:");
        double price = Double.parseDouble(scanner.next());

        System.out.println("Enter shelf location:");
        String shelfLocation = scanner.nextLine();

        String statusStr = "Available";
        Status status = Status.valueOf(statusStr); // Assuming Status is an enum
        
        String randomString = UUID.randomUUID().toString();
		int desiredLength = 8;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String bookId = randomString.substring(0, desiredLength);
        // Create a new book object
        Book book = new Book(bookId, bookTitle, author, publication, edition, price, shelfLocation, status);
        bookController.addBook(book);
	}
	
	private void updateUser()
	{
		userController.updateUser();
	}
	private void deleteNotification() {
		// TODO Auto-generated method stub
		System.out.println("Enter user id: ");
		String userId = scanner.next();
		notificationController.deleteNotification(userId);
	}

	private void sendNotification() {
		// TODO Auto-generated method stub
		scanner.nextLine();
		System.out.println("Enter user id to send notification: ");
		String userId = scanner.next();
		System.out.println("Enter title: ");
		String title = scanner.nextLine();
		System.out.println("Enter message: ");
		String message = scanner.nextLine();
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 7;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String notificationId = randomString.substring(0, desiredLength);
		LocalDate todayDate = LocalDate.now();
		notificationId = 'N' + notificationId;
		Notification notification = new Notification(notificationId,userId,title,message,todayDate);
		notificationController.sendNotification(notification);
	}
	private void readNotifications() {
		String userId = user.getUserId();
		notificationController.readNotifications(userId);
	}
	
}