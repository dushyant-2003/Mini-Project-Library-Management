package com.library.service;

import java.util.List;
import java.util.Scanner;

import com.library.constants.StringConstants;
import com.library.dao.InterfaceNotificationDAO;
import com.library.dao.NotificationDAO;
import com.library.model.Notification;

public class NotificationService {

	private InterfaceNotificationDAO notificationDAO;
	Scanner scanner;

	public NotificationService(InterfaceNotificationDAO notificationDAO) {
		// TODO Auto-generated constructor stub
		this.notificationDAO = notificationDAO;
		scanner = new Scanner(System.in);
	}

	public void sendNotification(Notification notification) {

		boolean sendStatus = notificationDAO.sendNotification(notification);
		if (sendStatus) {
			System.out.println(StringConstants.NOTIFICATION_SENT_SUCCESSFULLY);
		} else
			System.out.println(StringConstants.NOTIFCATION_NOT_SENT);
	}

	public List<Notification> readNotifications(String userId) {
		List<Notification> notificationList = notificationDAO.readNotifications(userId);
		return notificationList;
	}

	public boolean deleteNotification(String notificationId) {

		return notificationDAO.deleteNotification(notificationId);

	}

}
