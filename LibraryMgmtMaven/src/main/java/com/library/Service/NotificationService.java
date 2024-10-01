package com.library.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.library.Constants.StringConstants;
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
		System.out.println(StringConstants.NOTIFICATION_SENT_SUCCESSFULLY);
	} else 
		System.out.println(StringConstants.NOTIFCATION_NOT_SENT);
	}



public List<Notification> readNotifications(String userId) {
	List<Notification> notificationList = notificationDAO.readNotifications(userId);
	return notificationList;
}



public boolean deleteNotification(String notificationId) {
	
	boolean deleteStatus = false;
	
	try {
		deleteStatus = notificationDAO.deleteNotification(notificationId);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return deleteStatus;
}
	
	
}

  

