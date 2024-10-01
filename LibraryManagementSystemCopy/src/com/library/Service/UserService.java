package com.library.Service;


import java.sql.SQLException;
import java.util.List;

import com.library.DAO.UserDAO;
import com.library.Model.User;
 
public class UserService {
	private UserDAO userDAO;
 
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	public boolean authenticateUser(String username, String password, String role) {
		User user = null;
		try {
			user = userDAO.getUserByUserName(username);
			if(user != null)
			{
				if(user.getUserName().equals(username) && user.getPassword().equals(password) && user.getRole().name().equals(role))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}



	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list = userDAO.getAllUser();
		
		return list;
	}

	
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		boolean isDeletedSuccessfully = false;
		try {
			isDeletedSuccessfully = userDAO.deleteUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isDeletedSuccessfully;
	}

	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}



	public User getUserById(String userName) {
		// TODO Auto-generated method stub
		try {
			return userDAO.getUserById(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("User not found");
		}
		return null;
	}



	public void updateUser(User user, String userId, String columnToUpdate) {
		// TODO Auto-generated method stub
		try {
			// String userId = user.getUserId();
			userDAO.updateUser(user, userId, columnToUpdate);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
