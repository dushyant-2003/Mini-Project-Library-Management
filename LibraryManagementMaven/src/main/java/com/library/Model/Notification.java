package com.library.Model;

import java.time.LocalDate;

public class Notification {
	private String notificationId;
	private String userId;
	private String title;
	private String message;
	private LocalDate date;
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Notification(String notificationId, String userId, String title, String message, LocalDate date) {
		
		this.notificationId = notificationId;
		this.userId = userId;
		this.title = title;
		this.message = message;
		this.date = date;
	}
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	
}
