package com.library.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.library.dao.BookDAO;
import com.library.dao.BookIssueDAO;
import com.library.dao.NotificationDAO;
import com.library.dao.UserDAO;
import com.library.dao.InterfaceBookDAO;
import com.library.dao.InterfaceBookIssueDAO;
import com.library.dao.InterfaceNotificationDAO;
import com.library.dao.InterfaceUserDAO;
import com.library.constants.MenuConstants;
import com.library.constants.StringConstants;
import com.library.controller.BookController;
import com.library.controller.NotificationController;
import com.library.controller.UserController;
import com.library.model.Book;
import com.library.model.Gender;
import com.library.model.IssuedBookDetails;
import com.library.model.Notification;
import com.library.model.Role;
import com.library.model.Status;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.NotificationService;
import com.library.service.UserService;
import com.library.util.IdGenerator;
import com.library.util.InputIndex;
import com.library.util.PasswordUtil;
import com.library.util.PasswordValidator;
import com.library.util.UserValidator;
import com.library.util.printer.BookPrinter;
import com.library.util.printer.IssuedBookPrinter;
import com.library.util.printer.NotificationPrinter;
import com.library.util.printer.UserPrinter;

public class UserUI {

	InterfaceUserDAO userDAO = new UserDAO();
	InterfaceBookDAO bookDAO = new BookDAO();
	InterfaceBookIssueDAO bookIssueDAO = new BookIssueDAO();
	InterfaceNotificationDAO notificationDAO = new NotificationDAO();
	UserService userService = new UserService(userDAO, notificationDAO, bookDAO,bookIssueDAO);
	BookService bookService = new BookService(bookDAO,userDAO,bookIssueDAO);
	NotificationService notificationService = new NotificationService(notificationDAO);
	UserController userController = new UserController(userService);
	BookController bookController = new BookController(bookService);
	NotificationController notificationController = new NotificationController(notificationService);

	Scanner scanner = new Scanner(System.in);

	public UserUI() {

	}

	public void displayClientMenu(User user) {
		// TODO Auto-generated method stub

		while (true) {
			System.out.println(MenuConstants.CLIENT_MENU);
			System.out.println(StringConstants.INPUT_CHOICE);
			scanner.nextLine();
			String choice = scanner.next();
			switch (choice) {
			case StringConstants.CASE_1:
				readNotifications(user);
				break;
			case StringConstants.CASE_2:
				getIssuedBooks(user);
				break;
			case StringConstants.CASE_3:
				payFine(user);
				break;
			case StringConstants.CASE_4:
				System.out.println(StringConstants.LOGOUT_SUCCESSFUL);
				return;
			case StringConstants.CASE_5:
				System.out.println(StringConstants.THANK_YOU_MESSAGE);
				System.exit(0);
				break;
			default:
				System.out.println(StringConstants.VALID_CHOICE);
			}
		}
	}

	public void displayStaffMenu(User user) {

		while (true) {
			System.out.println(MenuConstants.STAFF_MENU);
			System.out.println(StringConstants.INPUT_CHOICE);
			scanner.nextLine();
			String choice = scanner.next();

			switch (choice) {
			case StringConstants.CASE_1:
				getUserByUserName();
				break;
			case StringConstants.CASE_2:
				addBook();
				break;
			case StringConstants.CASE_3:
				issueBook();
				break;
			case StringConstants.CASE_4:
				returnBook();
				break;
			case StringConstants.CASE_5:
				getAllIssuedBooks();
				break;
			case StringConstants.CASE_6:
				System.out.println(StringConstants.LOGOUT_SUCCESS);
				return;
			case StringConstants.CASE_7:
				System.out.println(StringConstants.EXIT_MESSAGE);
				System.exit(0);
			}
		}
	}

	public void displayAdminMenu(User user) {

		while (true) {
			System.out.println(MenuConstants.ADMIN_MENU);
			System.out.println(StringConstants.INPUT_CHOICE);
			String choice = scanner.next();

			switch (choice) {
			case StringConstants.CASE_1:
				addUser();
				break;
			case StringConstants.CASE_2:
				getUserByUserName();
				break;
			case StringConstants.CASE_3:
				getAllUsers();
				break;
			case StringConstants.CASE_4:
				deleteUser();
				break;
			case StringConstants.CASE_5:
				updateUser();
				break;
			case StringConstants.CASE_6:
				addBook();
				break;
			case StringConstants.CASE_7:
				issueBook();
				break;
			case StringConstants.CASE_8:
				returnBook();
				break;
			case StringConstants.CASE_9:
				getAllIssuedBooks();
				break;
			case StringConstants.CASE_10:
				sendNotification();
				break;
			case StringConstants.CASE_11:
				deleteNotification();
				break;
			case StringConstants.CASE_12:
				System.out.println(StringConstants.LOGOUT_SUCCESS);
				return;
			case StringConstants.CASE_13:
				System.exit(0);
			default:
				System.out.println(StringConstants.VALID_CHOICE);
			}
		}
	}

	private void addUser() {
		// TODO Auto-generated method stub
		String userId = IdGenerator.generateUserId();
		scanner.nextLine();

		System.out.println(StringConstants.INPUT_NAME);
		String name = scanner.nextLine();
		while (!UserValidator.isValidName(name)) {
			System.out.println(StringConstants.ENTER_VALID_NAME);
			name = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_USERNAME);
		String userName = scanner.nextLine();
		while (!UserValidator.isValidUsername(userName)) {
			System.out.println(StringConstants.ENTER_VALID_USERNAME);
			userName = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_ROLE);
		String role = scanner.nextLine().toUpperCase();
		while (!UserValidator.isValidRole(role)) {
			System.out.println(StringConstants.ENTER_VALID_ROLE);
			role = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_GENDER);
		String gender = scanner.nextLine(); //
		while (!UserValidator.isValidGender(gender)) {
			System.out.println(StringConstants.ENTER_VALID_GENDER);
			gender = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_DOB);
		String dob = scanner.nextLine();
		while (!UserValidator.isValidDate(dob)) {
			System.out.println(StringConstants.ENTER_VALID_DATE);
			dob = scanner.nextLine();
		}
		LocalDate dateOfBirth = LocalDate.parse(dob);

		System.out.println(StringConstants.INPUT_DEPARTMENT);
		String department = scanner.nextLine();
		while (!UserValidator.isValidName(department)) {
			System.out.println(StringConstants.INPUT_VALID_DEPARTMENT);
			department = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_DESIGNATION);
		String designation = scanner.nextLine();
		while (!UserValidator.isValidName(designation)) {
			System.out.println(StringConstants.INPUT_VALID_DESIGNATION);
			designation = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_CONTACT_NUMBER);
		String contactNumber = scanner.nextLine();
		while (!UserValidator.isValidPhoneNo(contactNumber)) {
			System.out.println(StringConstants.ENTER_VALID_COTACT_NUMBER);
			contactNumber = scanner.nextLine();
		}

		System.out.println(StringConstants.ENTER_EMAIL);
		String email = scanner.nextLine();
		while (!UserValidator.isValidEmail(email)) {
			System.out.println(StringConstants.ENTER_VALID_EMAIL);
			email = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_ADDRESS);
		String address = scanner.nextLine();

		System.out.println(StringConstants.INPUT_BOOK_ISSUE_LIMIT);
		String bookIssueLimit = scanner.nextLine();
		while (!UserValidator.isValidBookIssueLimit(bookIssueLimit)) {
			System.out.println(StringConstants.ENTER_VALID_LIMIT);
			scanner.nextLine();
			bookIssueLimit = scanner.nextLine();
		}
		int bookIssueLimitInteger = Integer.parseInt(bookIssueLimit);

		BigDecimal fine = new BigDecimal(StringConstants.ZERO_VALUE);

		System.out.println(StringConstants.INPUT_PASSWORD);
		String password = scanner.nextLine();

		while (!PasswordValidator.isValidPassword(password)) {
			System.out.println(StringConstants.ENTER_VALID_PASSWORD);
			password = scanner.nextLine();
		}
		String hashedPassword = PasswordUtil.hashPassword(password);

		User user = new User(userId, name, userName, Role.valueOf(role.toUpperCase()),
				Gender.valueOf(gender.toUpperCase()), dateOfBirth, department, designation, contactNumber, email,
				address, bookIssueLimitInteger, fine, hashedPassword);

		userController.addUser(user);

	}

	private void returnBook() {

		List<User> userList = getAllIssuers();
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);
		int limit = userList.size();
		int userIndex = InputIndex.inputIndex(limit);
		User user = userList.get(userIndex - 1);
		List<IssuedBookDetails> issuedBooks = bookController.getAllIssuedBookByUserName(user.getUserName());

		IssuedBookPrinter.printIssuedBooks(issuedBooks);

		System.out.println(StringConstants.INPUT_INDEX_OF_BOOK);
		// System.out.println(StringConstants.ENTER_B_TO_BACK);
		limit = issuedBooks.size();
		int bookIndex = InputIndex.inputIndex(limit);

		IssuedBookDetails book = issuedBooks.get(bookIndex - 1);

		System.out.println("Is the book lost? (y/n): ");
		String isBookLost = scanner.nextLine();
		while (!UserValidator.isValidYesNo(isBookLost)) {
			System.out.println(StringConstants.VALID_CHOICE);
			isBookLost = scanner.nextLine();
		}
		boolean bookLostStatus = isBookLost.equalsIgnoreCase("y");
		bookController.returnBook(book, user, bookLostStatus);
	}

	private void issueBook() {

		List<Book> availableBookList = new ArrayList<Book>();

		availableBookList = bookController.getAllBooks();
		BookPrinter.printBooks(availableBookList);
		System.out.println(StringConstants.INPUT_INDEX_OF_BOOK);

		int limit = availableBookList.size();

		int bookIndex = InputIndex.inputIndex(limit);

		Book book = availableBookList.get(bookIndex - StringConstants.MINIMUM_INDEX);

		List<User> userList = getAllIssuers();
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);

		limit = userList.size();
		int userIndex = InputIndex.inputIndex(limit);

		User issuer = userList.get(userIndex - 1);

		boolean status = bookController.issueBook(book, issuer);
		if (status) {
			System.out.printf(StringConstants.SUCCESS_ISSUE_BOOK, issuer.getBookIssueLimit());
		} else {
			System.out.println(StringConstants.FAILURE_ISSUE_BOOK);
		}
	}

	private List<User> getAllIssuers() {
		// TODO Auto-generated method stub
		List<User> userList = userController.getAll();
		if (userList.size() == 0) {
			System.out.println(StringConstants.USER_NOT_FOUND);
		}
		UserPrinter.printUsers(userList);
		return userList;
	}

	private void deleteUser() {
		// TODO Auto-generated method stub

		List<User> userList = getAllIssuers();
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);
		int limit = userList.size();
		int userIndex = InputIndex.inputIndex(limit);
		boolean status = userController.deleteUser(userList.get(userIndex - StringConstants.MINIMUM_INDEX).getUserId());

		if (status) {
			System.out.println(StringConstants.USER_DELETED_SUCCEESS);
		} else {
			System.out.println(StringConstants.USER_NOT_DELETED);
		}
	}

	private List<User> getAllUsers() {
		System.out.println(StringConstants.SEE_DETAILS_OF_STAFF_MEMBERS);
		System.out.println(StringConstants.SEE_DETAILS_OF_ISSUERS);
		scanner.nextLine();
		String choice = scanner.next();
		String role = StringConstants.EMPTY_STRING;

		switch (choice) {
		case StringConstants.CASE_1:
			role = StringConstants.STAFF_ROLE;
			break;
		case StringConstants.CASE_2:
			role = StringConstants.ISSUER_ROLE;
			break;
		default:
			System.out.println(StringConstants.VALID_CHOICE);
		}
		System.out.println(StringConstants.TABLE_BORDER);
		List<User> users = userController.getAllUsers(role);

		UserPrinter.printUsers(users);
		return users;

	}

	private void getUserByUserName() {
		System.out.println(StringConstants.USERNAME_INPUT);
		String userId = scanner.next();

		User user = userController.getUserByUserName(userId);
		if (user == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
		} else {
			UserPrinter.printUsers(List.of(user));
		}

	}

	private void addBook() {
		scanner.nextLine();

		System.out.println(StringConstants.INPUT_BOOK_TITLE);
		String bookTitle = scanner.nextLine();
		while (!UserValidator.isValidName(bookTitle)) {
			System.out.println(StringConstants.ENTER_VALID_NAME);
			bookTitle = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_AUTHOR);
		String author = scanner.nextLine();
		while (!UserValidator.isValidName(bookTitle)) {
			System.out.println(StringConstants.ENTER_VALID_NAME);
			author = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_PUBLICATION);
		String publication = scanner.nextLine();
		while (!UserValidator.isValidName(bookTitle)) {
			System.out.println(StringConstants.ENTER_VALID_NAME);
			publication = scanner.nextLine();
		}

		System.out.println(StringConstants.INPUT_EDITION);
		String edition = scanner.nextLine();

		System.out.println(StringConstants.INPUT_PRICE);
		double price = -1;
		do {
			try {
				price = Double.parseDouble(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println(StringConstants.ENTER_VALID_PRICE);
			}
		} while (!UserValidator.isValidFine(price));

		scanner.nextLine();

		System.out.println(StringConstants.INPUT_SHELF_LOCATION);
		String shelfLocation = scanner.nextLine();

		String statusStr = StringConstants.AVAILABLE_STATUS;
		Status status = Status.valueOf(statusStr); // Assuming Status is an enum

		String bookId = IdGenerator.generateBookId();

		Book book = new Book(bookId, bookTitle, author, publication, edition, price, shelfLocation, status);
		bookController.addBook(book);
	}

	private void updateUser() {
		userController.updateUser();
	}

	private void deleteNotification() {
		// TODO Auto-generated method stub
		List<User> userList = getAllIssuers();
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);

		int limit = userList.size();
		int userIndex = InputIndex.inputIndex(limit);

		String userId = userList.get(userIndex - 1).getUserId();
		List<Notification> notificationList = notificationController.readNotifications(userId);

		NotificationPrinter.printNotifications(notificationList);

		System.out.println(StringConstants.INPUT_INDEX_OF_NOTIFICATION);

		limit = notificationList.size();
		int notificationIndex = InputIndex.inputIndex(limit);

		notificationController.deleteNotification(
				notificationList.get(notificationIndex - StringConstants.MINIMUM_INDEX).getNotificationId());
		System.out.println(StringConstants.NOTIFICATION_DELETED_SUCCESS);
	}

	private void sendNotification() {
		// TODO Auto-generated method stub

		List<User> userList = getAllIssuers();
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);

		int limit = userList.size();
		int userIndex = InputIndex.inputIndex(limit);

		String userName = userList.get(userIndex - 1).getUserName();

		User user = userController.getUserByUserName(userName);
		if (user == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
			return;
		}
		scanner.nextLine();
		System.out.println(StringConstants.INPUT_TITLE_NOTIFY);
		String title = scanner.nextLine();
		System.out.println(StringConstants.ENTER_MESSAGE_NOTIFY);
		String message = scanner.nextLine();

		String notificationId = IdGenerator.generateNotificationId();

		LocalDate todayDate = LocalDate.now();

		Notification notification = new Notification(notificationId, user.getUserId(), title, message, todayDate);
		notificationController.sendNotification(notification);
	}

	private void readNotifications(User user) {
		String userId = user.getUserId();
		List<Notification> notificationList = notificationController.readNotifications(userId);
		if (notificationList.isEmpty()) {
			System.out.println(StringConstants.NO_NOTIFICATIONS_FOUND);
			return;
		}
		NotificationPrinter.printNotifications(notificationList);

	}

	private void payFine(User user) {

		userController.payFine(user);
	}

	private void getIssuedBooks(User user) {
		// TODO Auto-generated method stub

		List<IssuedBookDetails> issuedBooksDetails = bookController.getIssuedBook(user);
		if (issuedBooksDetails.size() == 0) {
			System.out.println("No books issued");
		}
		IssuedBookPrinter.printIssuedBooks(issuedBooksDetails);

	}

	private void getAllIssuedBooks() {
		boolean seeDefaulterList = false;
		System.out.println(StringConstants.ENTER_1_TO_SEE_DEFAULTER_LIST);
		System.out.println(StringConstants.ENTER_2_TO_SEE_ISSUED_BOOKS);

		String choice = scanner.next();
		switch (choice) {
		case StringConstants.CASE_1:
			seeDefaulterList = true;
			break;
		case StringConstants.CASE_2:
			seeDefaulterList = false;
			break;
		default:
			System.out.println(StringConstants.VALID_CHOICE);

		}
		List<IssuedBookDetails> issuedBooksDetails = bookController.getAllIssuedBook(seeDefaulterList);
		IssuedBookPrinter.printIssuedBooks(issuedBooksDetails);
	}

	public User authenticateUser(String username, String password) {

		User user = userController.authenticateUser(username, password);
		return user;
	}

}