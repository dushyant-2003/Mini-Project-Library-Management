package com.library.controller;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.library.constants.MenuConstants;
import com.library.constants.StringConstants;
import com.library.constants.UserConstants;
import com.library.model.Gender;
import com.library.model.Role;
import com.library.model.User;
import com.library.service.UserService;
import com.library.util.PasswordUtil;
import com.library.util.PasswordValidator;
import com.library.util.UserValidator;
import com.library.util.printer.UserPrinter;

public class UserController {

	private UserService userService;
	private Scanner scanner = new Scanner(System.in);

	public UserController() {

	}

	public UserController(UserService userService) {
		this.userService = userService;

	}

	public User authenticateUser(String username, String password) {

		if (username == null || password == null) {
			System.out.println(StringConstants.PLEASE_FILL_OUT_ALL_THE_FIELDS);
			return null;
		}

		boolean isValidPassword = PasswordValidator.isValidPassword(password);

		if (!isValidPassword) {
			System.out.println(StringConstants.INPUT_VALID_PASSWORD);
			return null;
		}
		return userService.authenticateUser(username, password);
	}

	public List<User> getAllUsers(String role) {
		// TODO Auto-generated method stub
		List<User> list = userService.getAllUsers(role);
		return list;

	}

	public void addUser(User user) {

		boolean addStatus = userService.addUser(user);
		if (addStatus) {
			System.out.println(StringConstants.USER_ADDED_SUCCESSFULLY);
		} else {
			System.out.println(StringConstants.USER_NOT_ADDED);
		}
	}

	public List<User> getAll() {
		return userService.getAll();
	}

	public void updateUser() {

		List<User> allUsers = userService.getAll();
		UserPrinter.printUsers(allUsers);
		System.out.println(StringConstants.INPUT_INDEX_OF_USER);
		boolean validateIndex = false;
		int limit = allUsers.size();
		int userIndex = 0;
		while (!validateIndex) {
			try {
				userIndex = scanner.nextInt();
				if (UserValidator.isValidIndex(userIndex, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INDEX);
				scanner.next();
			}
		}

		User user = userService.getUserByUserName(allUsers.get(userIndex - 1).getUserName());

		if (user != null) {
			boolean continueUpdating = true;

			while (continueUpdating) {
				System.out.println(MenuConstants.UPDATE_MENU);

				System.out.print(StringConstants.SELECT_UPDATE_OPTION);
				String choice = scanner.nextLine();

				String columnToUpdate = StringConstants.EMPTY_STRING;
				Runnable updateAction = null;

				switch (choice) {
				case StringConstants.CASE_1:
					columnToUpdate = UserConstants.NAME_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_NAME);
						String name = scanner.nextLine();
						while (!UserValidator.isValidName(name)) {
							System.out.println(StringConstants.ENTER_VALID_NAME);
							name = scanner.nextLine();
						}
						user.setName(name);
					};
					break;
				case StringConstants.CASE_2:
					columnToUpdate = UserConstants.EMAIL_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.ENTER_EMAIL);
						String email = scanner.nextLine();

						while (!UserValidator.isValidEmail(email)) {
							System.out.println(StringConstants.ENTER_VALID_EMAIL);
							email = scanner.nextLine();
						}
						user.setEmail(email);
					};
					break;
				case StringConstants.CASE_3:
					columnToUpdate = UserConstants.USERNAME_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_USERNAME);
						String userName = scanner.nextLine();
						while (!UserValidator.isValidUsername(userName)) {
							System.out.println(StringConstants.ENTER_VALID_USERNAME);
							userName = scanner.nextLine();
						}
						user.setUserName(userName);
					};
					break;
				case StringConstants.CASE_4:
					columnToUpdate = UserConstants.PASSWORD_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_PASSWORD);
						String password = scanner.nextLine();

						while (!PasswordValidator.isValidPassword(password)) {
							System.out.println(StringConstants.ENTER_VALID_PASSWORD);
							password = scanner.nextLine();
						}
						String hashedPassword = PasswordUtil.hashPassword(password);
						user.setPassword(hashedPassword);
					};

					break;
				case StringConstants.CASE_5:
					columnToUpdate = UserConstants.DEPARTMENT_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_DEPARTMENT);
						String department = scanner.nextLine();
						while (!UserValidator.isValidName(department)) {
							System.out.println(StringConstants.INPUT_VALID_DEPARTMENT);
							department = scanner.nextLine();
						}
						user.setDepartment(department);
					};
					break;
				case StringConstants.CASE_6:
					columnToUpdate = UserConstants.DESIGNATION_COLUMN;
					updateAction = () -> {
						String designation = scanner.nextLine();
						while (!UserValidator.isValidName(designation)) {
							System.out.println(StringConstants.INPUT_VALID_DESIGNATION);
							designation = scanner.nextLine();
						}
						user.setDesignation(designation);
					};
					break;
				case StringConstants.CASE_7:
					columnToUpdate = UserConstants.CONTACT_NO_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_CONTACT_NUMBER);
						String contactNumber = scanner.nextLine();
						while (!UserValidator.isValidPhoneNo(contactNumber)) {
							System.out.println(StringConstants.ENTER_VALID_COTACT_NUMBER);
							contactNumber = scanner.nextLine();
						}
						user.setContactNumber(contactNumber);
					};
					break;
				case StringConstants.CASE_8:
					columnToUpdate = UserConstants.ADDRESS_COLUMN;
					updateAction = () -> {
						String bookIssueLimit = scanner.nextLine();
						while (!UserValidator.isValidBookIssueLimit(bookIssueLimit)) {
							System.out.println(StringConstants.ENTER_VALID_LIMIT);
							scanner.nextLine();
							bookIssueLimit = scanner.nextLine();
						}
						int bookIssueLimitInteger = Integer.parseInt(bookIssueLimit);
						user.setBookIssueLimit(bookIssueLimitInteger);
					};
					break;
				case StringConstants.CASE_9:
					columnToUpdate = UserConstants.ROLE_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_ROLE);
						String role = scanner.nextLine().toUpperCase();
						while (!UserValidator.isValidRole(role)) {
							System.out.println(StringConstants.ENTER_VALID_ROLE);
							role = scanner.nextLine();
						}
						user.setRole(Role.valueOf(role));
					};
					break;
				case StringConstants.CASE_10:
					columnToUpdate = UserConstants.FINE_COLUMN;
					updateAction = () -> {
						System.out.println(StringConstants.INPUT_FINE);
						double fine = scanner.nextDouble();
						while (!UserValidator.isValidFine(fine)) {
							System.out.println(StringConstants.INPUT_VALID_FINE);
							fine = scanner.nextDouble();
						}
						user.setFine(BigDecimal.valueOf(fine));
					};
					break;
				case StringConstants.CASE_11:
					columnToUpdate = UserConstants.BOOK_ISSUE_LIMIT_COLUMN;
					updateAction = () -> {
						String bookIssueLimit = scanner.nextLine();
						while (!UserValidator.isValidBookIssueLimit(bookIssueLimit)) {
							System.out.println(StringConstants.ENTER_VALID_LIMIT);
							scanner.nextLine();
							bookIssueLimit = scanner.nextLine();
						}
						int bookIssueLimitInteger = Integer.parseInt(bookIssueLimit);
						user.setBookIssueLimit(bookIssueLimitInteger);
					};
					break;
				case StringConstants.CASE_12:
					columnToUpdate = UserConstants.GENDER_COLUMN;
					updateAction = () -> {
						String gender = scanner.nextLine(); //
						while (!UserValidator.isValidGender(gender)) {
							System.out.println(StringConstants.ENTER_VALID_GENDER);
							gender = scanner.nextLine();
						}
						user.setGender(Gender.valueOf(gender));
					};
					break;
				case StringConstants.CASE_13:
					continueUpdating = false;
					break;
				default:
					System.out.println(StringConstants.INVALID_OPTION);
				}

				if (updateAction != null) {
					updateAction.run();
					boolean updateStatus = userService.updateUser(user, user.getUserId(), columnToUpdate);
					if (updateStatus) {
						System.out.println(StringConstants.USER_UPDATED_SUCCESSFULLY);
					} else {
						System.out.println(StringConstants.USER_NOT_UPDATED);
					}
				}
			}
		} else {
			System.out.println(StringConstants.USER_NOT_FOUND);
		}

	}

	public boolean deleteUser(String userId) {

		if (userId == null) {

			return false;
		}
		boolean isDeletedSuccessfully = userService.deleteUser(userId);
		return isDeletedSuccessfully;
	}

	public User getUserByUserName(String userName) {

		if (userName == null) {
			return null;
		}
		return userService.getUserByUserName(userName);
	}

	public void payFine(User user) {

		userService.payFine(user);
	}

}
