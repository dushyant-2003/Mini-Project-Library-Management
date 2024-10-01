package com.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.library.model.User;

public interface InterfaceUserDAO {
	User getUserByUserName(String username);
    User getUserByUserId(String userId);
    List<User> getAllUser(String role);
    boolean deleteUser(String userId) throws SQLException;
    boolean addUser(User user);
    boolean updateUser(User user, String userId, String columnToUpdate);
    List<User> getAll();
}
