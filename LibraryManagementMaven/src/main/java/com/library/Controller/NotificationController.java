package com.library.Controller;

import java.util.List;
import com.library.Constants.StringConstants;
import com.library.Model.Notification;
import com.library.Service.NotificationService;

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
