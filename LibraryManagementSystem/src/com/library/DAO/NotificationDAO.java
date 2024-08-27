package com.library.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.Model.Notification;
import com.library.Model.User;

public class NotificationDAO {

	private Connection connection;

	public NotificationDAO() throws ClassNotFoundException, SQLException {
		this.connection = DBConnectionManager.getConnection();
		
	}
	public boolean sendNotification(Notification notification)
	{	
			String query = "INSERT INTO Notification (notificationId, userId, type ,message,date) VALUES (?,?,?,?,?)";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, notification.getNotificationId());
				stmt.setString(2, notification.getUserId());
				stmt.setString(3, notification.getTitle());
				stmt.setString(4, notification.getMessage());
				stmt.setDate(5, Date.valueOf(notification.getDate()));
	 
				return stmt.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			
		}
			return false;	
	}
	public List<Notification> readNotifications(String userId) {
	    String query = "SELECT * FROM Notification WHERE userId = ?";
	    List<Notification> notificationList = new ArrayList<>();
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setString(1, userId);
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            while (resultSet.next()) {
	                Notification notification = new Notification();
	                notification.setNotificationId(resultSet.getString("notificationId"));
	                notification.setUserId(resultSet.getString("userId"));
	                notification.setTitle(resultSet.getString("type"));
	                notification.setMessage(resultSet.getString("message"));
	                notification.setDate(resultSet.getDate("date").toLocalDate());

	                
	                notificationList.add(notification);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Cannot fetch notications");
	    }
		return notificationList;
	}
	public boolean deleteNotification(String notificationId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Notification WHERE notificationId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, notificationId);
            return stmt.executeUpdate() > 0; // Returns true if a row was deleted
        }
		
	}


}
