package com.library.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.library.constants.StringConstants;
import com.library.dao.InterfaceBookDAO;
import com.library.dao.InterfaceBookIssueDAO;
import com.library.dao.InterfaceNotificationDAO;
import com.library.dao.InterfaceUserDAO;
import com.library.model.IssuedBookDetails;
import com.library.model.Notification;
import com.library.model.User;
import com.library.util.IdGenerator;
import com.library.util.LoggingUtil;
import com.library.util.PasswordUtil;

public class UserService {

	private InterfaceUserDAO userDAO;
	private InterfaceNotificationDAO notificationDAO;
	private InterfaceBookDAO bookDAO;
	private InterfaceBookIssueDAO bookIssueDAO;
	public static Logger logger = LoggingUtil.getLogger(UserService.class);

	public UserService(InterfaceUserDAO userDAO, InterfaceNotificationDAO notificationDAO, InterfaceBookDAO bookDAO,
			InterfaceBookIssueDAO bookIssueDAO) {
		this.userDAO = userDAO;
		this.notificationDAO = notificationDAO;
		this.bookDAO = bookDAO;
		this.bookIssueDAO = bookIssueDAO;
	}

	public User authenticateUser(String username, String password) {
		User user;
		user = userDAO.getUserByUserName(username);

		if (user != null && user.getUserName().equals(username)
				&& PasswordUtil.checkPassword(password, user.getPassword())) {

			LocalDate today = LocalDate.now();
			List<IssuedBookDetails> issuedBookDetailsCrossingDeadlines = bookIssueDAO.getIssuedBook(user.getUserId())
					.stream().filter(issuedBook -> issuedBook.getDeadline().isBefore(today))
					.collect(Collectors.toList());

			String notificationId = IdGenerator.generateNotificationId();
			int noOfBooks = issuedBookDetailsCrossingDeadlines.size();
			LocalDate todayDate = LocalDate.now();
			String title = "Return Book";
			String message = "You have " + noOfBooks + " books that have crossed the due dates";

			Notification notification = new Notification(notificationId, user.getUserId(), title, message, todayDate);
			if (noOfBooks > 0) {
				notificationDAO.sendNotification(notification);
			}

			return user;

		}
		return null;

	}

	public List<User> getAllUsers(String role) {
		// TODO Auto-generated method stub

		if (StringConstants.STAFF_ROLE.equalsIgnoreCase(role) || StringConstants.ISSUER_ROLE.equalsIgnoreCase(role)) {
			List<User> list = userDAO.getAllUser(role);
			return list;
		}

		return null;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDAO.getAll();
	}

	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		User user = null;
		user = userDAO.getUserByUserId(userId);
		if (user == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
			return false;
		}
		if (user.getRole().toString().equalsIgnoreCase(StringConstants.ADMIN_ROLE)) {
			System.out.println("Admin cannot be deleted");
			return false;
		}
		try {
			return userDAO.deleteUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addUser(User user) {
		boolean userAddStatus = userDAO.addUser(user);
		if (!userAddStatus) {
			return false;
		}
		String notificationId = IdGenerator.generateNotificationId();

		String title = "Welcome";
		String message = "Hi " + user.getName();
		LocalDate todayDate = LocalDate.now();
		Notification welcomeMessage = new Notification(notificationId, user.getUserId(), title, message, todayDate);
		notificationDAO.sendNotification(welcomeMessage);
		return userAddStatus;

	}

	public User getUserByUserName(String userName) {
		return userDAO.getUserByUserName(userName);

	}

	public boolean updateUser(User user, String userId, String columnToUpdate) {

		return userDAO.updateUser(user, userId, columnToUpdate);

	}

	public void payFine(User user) {
		BigDecimal fine = user.getFine();
		BigDecimal zeroValue = new BigDecimal("0");

		if (fine.compareTo(zeroValue) == 0) {
			System.out.println("You have no pending dues");
			return;
		}
		System.out.println("Your fine is Rs. " + fine.toString());
		user.setFine(zeroValue);

		boolean fineUpdateStatus = updateUser(user, user.getUserId(), "fine");
		if (fineUpdateStatus) {
			logger.info("Fine paid successfully of user " + user.getName());
		} else {
			logger.severe("Failed to update fine status for user " + user.getName());
		}

	}

}
