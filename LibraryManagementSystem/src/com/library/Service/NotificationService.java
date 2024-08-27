package com.library.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.library.DAO.NotificationDAO;
import com.library.Model.Notification;

public class NotificationService {
	private NotificationDAO notificationDAO;
	Scanner scanner;


  public NotificationService(NotificationDAO notificationDAO) {
		// TODO Auto-generated constructor stub
  	this.notificationDAO = notificationDAO;
  	scanner = new Scanner(System.in);
	}



public void sendNotification(Notification notification) {
	
	boolean sendStatus = notificationDAO.sendNotification(notification);
	if(sendStatus) {
		System.out.println("Notification sent successfully");
	} else 
		System.out.println("Notifcation not sent");
	}



public boolean readNotifications(String userId) {
	List<Notification> notificationList = notificationDAO.readNotifications(userId);
	if(notificationList.isEmpty()) {
		System.out.println("No notifications");
		return false;
	} else {
		for(Notification notification: notificationList) {
			System.out.println("Notification Id: " + notification.getNotificationId());
			System.out.println("Notification: " + notification.getTitle());
            System.out.println("Message: " + notification.getMessage());
            System.out.println("Date: " + notification.getDate());
            System.out.println("------------------------");
            
		}
		return true;
	}
	
	
}



public void deleteNotification(String userId) {
	boolean isNotificationPresent = readNotifications(userId);
	
	if(isNotificationPresent) {
		System.out.println("Enter notification id to be deleted");
		String notificationId = scanner.next();
		try {
			notificationDAO.deleteNotification(notificationId);
			System.out.println("Notification deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Notification not deleted");
			e.printStackTrace();
		}
	
		
	}
	else {
		System.out.println("No notifications found");
	}
	
}
	
	
}

  

