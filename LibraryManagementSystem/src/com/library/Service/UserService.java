package com.library.Service;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.library.DAO.UserDAO;
import com.library.Model.User;
import com.library.util.PasswordUtil;
 
public class UserService {
	private UserDAO userDAO;
 
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	public User authenticateUser(String username, String password) {
		User user;
		try {
		    user = userDAO.getUserByUserName(username);
		    
		    if (user != null && user.getUserName().equals(username) && PasswordUtil.checkPassword(password,user.getPassword())) {
		        return user;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return null;
		
	}



	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list = userDAO.getAllUser();
		
		return list;
	}

	
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		User user = null;
		user = userDAO.getUserByUserId(userId);
		if(user == null) {
			System.out.println("User not found");
			return false;
		}
		if(user.getRole().toString().equalsIgnoreCase("ADMIN")) {
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
		return userDAO.addUser(user);
	}



	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		try {
			return userDAO.getUserByUserName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("User not found");
		}
		return null;
	}



	public void updateUser(User user, String userId, String columnToUpdate) {
		// TODO Auto-generated method stub
		
			// String userId = user.getUserId();
			userDAO.updateUser(user, userId, columnToUpdate);
	
	}



	public User getUserById(String userId) {
		return userDAO.getUserByUserId(userId);
		
	}



	public void payFine(User user) {
		BigDecimal fine = user.getFine();
		BigDecimal zeroValue = new BigDecimal("0");
		
		if(fine.compareTo(zeroValue) == 0) {
			System.out.println("You have no pending dues");
			return;
		}
		System.out.println("Your fine is Rs. " + fine.toString());
		user.setFine(zeroValue);
		
		// Set fine field in user to 0
		updateUser(user,user.getUserId(),"fine");
	}
}
