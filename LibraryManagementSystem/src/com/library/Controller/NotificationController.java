package com.library.Controller;

import java.util.Scanner;

import com.library.Model.Notification;
import com.library.Service.NotificationService;

public class NotificationController {
	private NotificationService notificationService;
	private Scanner scanner = new Scanner(System.in);
 
	public NotificationController() {
		
	}
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
		
	}
	
	public boolean sendNotification (Notification notification) {
		 notificationService.sendNotification(notification);
		return false;
	}
	public void readNotifications(String userId) {
		if(userId == null) {
			System.out.println("User not found");
		}
		notificationService.readNotifications(userId);
	}
	public void deleteNotification(String userId) {
		// TODO Auto-generated method stub
		if(userId == null) {
			System.out.println("Enter valid user id");
		}
		notificationService.deleteNotification(userId);
	}
	
}
