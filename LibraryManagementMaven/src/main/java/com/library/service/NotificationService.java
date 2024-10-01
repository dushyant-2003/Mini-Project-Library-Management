package com.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.constants.StringConstants;
import com.library.dao.InterfaceNotificationDAO;
import com.library.model.Notification;
import com.library.util.LoggingUtil;

public class NotificationService {

	private static final Logger logger = LoggingUtil.getLogger(NotificationService.class);

	private InterfaceNotificationDAO notificationDAO;
	private Scanner scanner;

	public NotificationService(InterfaceNotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
		this.scanner = new Scanner(System.in);
	}

	public void sendNotification(Notification notification) {
		logger.log(Level.INFO, "Attempting to send notification to user: {0}", notification.getUserId());

		boolean sendStatus = notificationDAO.sendNotification(notification);
		if (sendStatus) {
			logger.log(Level.INFO, "Notification sent successfully to user: {0}", notification.getUserId());
			System.out.println(StringConstants.NOTIFICATION_SENT_SUCCESSFULLY);
		} else {
			logger.log(Level.WARNING, "Failed to send notification to user: {0}", notification.getUserId());
			System.out.println(StringConstants.NOTIFCATION_NOT_SENT);
		}
	}

	public List<Notification> readNotifications(String userId) {
		logger.log(Level.INFO, "Reading notifications for user: {0}", userId);
		List<Notification> notificationList = notificationDAO.readNotifications(userId);
		if (notificationList.isEmpty()) {
			logger.log(Level.INFO, "No notifications found for user: {0}", userId);
		} else {
			logger.log(Level.INFO, "{0} notifications found for user: {1}",
					new Object[] { notificationList.size(), userId });
		}
		return notificationList;
	}

	public boolean deleteNotification(String notificationId) {
		logger.log(Level.INFO, "Attempting to delete notification with ID: {0}", notificationId);

		boolean deleteStatus = false;
		deleteStatus = notificationDAO.deleteNotification(notificationId);
		if (deleteStatus) {
			logger.log(Level.INFO, "Notification with ID: {0} deleted successfully", notificationId);
		} else {
			logger.log(Level.WARNING, "Failed to delete notification with ID: {0}", notificationId);
		}
		return deleteStatus;
	}
}
