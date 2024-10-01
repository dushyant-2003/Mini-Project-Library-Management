package com.library.controller;

import java.util.List;

import com.library.constants.StringConstants;
import com.library.model.Notification;
import com.library.service.NotificationService;

public class NotificationController {
	private NotificationService notificationService;
 
	public NotificationController() {
		
	}
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	public boolean sendNotification (Notification notification) {
		 notificationService.sendNotification(notification);
		return false;
	}
	public List<Notification> readNotifications(String userId) {
		if(userId == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
		}
		return notificationService.readNotifications(userId);
	}
	public boolean deleteNotification(String notificationId) {
		// TODO Auto-generated method stub
	
		return notificationService.deleteNotification(notificationId);
		
	}
	
}
