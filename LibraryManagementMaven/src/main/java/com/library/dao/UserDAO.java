package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.constants.UserConstants;
import com.library.model.Gender;
import com.library.model.Role;
import com.library.model.User;

public class UserDAO extends GenericDAO<User> implements InterfaceUserDAO{

	public UserDAO() {

	}

	protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		User user = null;
		user = new User();
		user.setUserId(resultSet.getString(UserConstants.USER_ID_COLUMN));
		user.setName(resultSet.getString(UserConstants.NAME_COLUMN));
		user.setUserName(resultSet.getString(UserConstants.USERNAME_COLUMN));
		user.setRole(Role.valueOf(resultSet.getString(UserConstants.ROLE_COLUMN).toUpperCase()));
		user.setGender(Gender.valueOf(resultSet.getString(UserConstants.GENDER_COLUMN).toUpperCase()));
		user.setDateOfBirth(resultSet.getDate(UserConstants.DATE_OF_BIRTH).toLocalDate());
		user.setDepartment(resultSet.getString(UserConstants.DEPARTMENT_COLUMN));
		user.setDesignation(resultSet.getString(UserConstants.DESIGNATION_COLUMN));
		user.setContactNumber(resultSet.getString(UserConstants.CONTACT_NO_COLUMN));
		user.setEmail(resultSet.getString(UserConstants.EMAIL_COLUMN));
		user.setAddress(resultSet.getString(UserConstants.ADDRESS_COLUMN));
		user.setBookIssueLimit(resultSet.getInt(UserConstants.BOOK_ISSUE_LIMIT_COLUMN));
		user.setFine(resultSet.getBigDecimal(UserConstants.FINE_COLUMN));
		user.setPassword(resultSet.getString(UserConstants.PASSWORD_COLUMN));
		return user;
	}

	public User getUserByUserName(String username) {
		String selectSQL = "SELECT * FROM User WHERE userName = \"" + username + "\"";
		try {
			return executeGetQuery(selectSQL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByUserId(String userId) {
		String selectSQL = "SELECT * FROM User WHERE userId = \"" + userId + "\"";
		try {
			return executeGetQuery(selectSQL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUser(String role) {
		List<User> users = new ArrayList<>();
		String selectAllSqlQuery = "SELECT * FROM user where role = \"" + role + "\"";
		try {
			return executeGetAllQuery(selectAllSqlQuery);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteUser(String userId) throws SQLException {
		// TODO Auto-generated method stub
		String selectSQL = "DELETE FROM User WHERE userid = \"" + userId + "\"";
		try {
			return executeUpdateQuery(selectSQL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addUser(User user) {

		String sqlQuery = String.format(
				"INSERT INTO User (userId, name, username, role, gender, dateOfBirth, department, designation, contactNumber, email, address, bookIssueLimit, password) VALUES ('%s','%s','%s','%s','%s','%s','%s', '%s','%s','%s','%s', '%s','%s')",
				user.getUserId(), user.getName(), user.getUserName(), user.getRole().toString(),
				user.getGender().toString(), java.sql.Date.valueOf(user.getDateOfBirth()), user.getDepartment(),
				user.getDesignation(), user.getContactNumber(), user.getEmail(), user.getAddress(),
				user.getBookIssueLimit(), user.getPassword());

		try {
			return executeUpdateQuery(sqlQuery);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean updateUser(User user, String userId, String columnToUpdate) {

		Map<String, Object> fieldMap = new HashMap<>();

		fieldMap.put(UserConstants.NAME_COLUMN, user.getName());
		fieldMap.put(UserConstants.USERNAME_COLUMN, user.getUserName());
		fieldMap.put(UserConstants.ROLE_COLUMN, user.getRole());
		fieldMap.put(UserConstants.GENDER_COLUMN, user.getGender());
		fieldMap.put(UserConstants.DATE_OF_BIRTH, user.getDateOfBirth());
		fieldMap.put(UserConstants.CONTACT_NO_COLUMN, user.getContactNumber());
		fieldMap.put(UserConstants.EMAIL_COLUMN, user.getEmail());
		fieldMap.put(UserConstants.ADDRESS_COLUMN, user.getAddress());
		fieldMap.put(UserConstants.BOOK_ISSUE_LIMIT_COLUMN, user.getBookIssueLimit());
		fieldMap.put(UserConstants.PASSWORD_COLUMN, user.getPassword());
		fieldMap.put(UserConstants.FINE_COLUMN, user.getFine());
		if (fieldMap.containsKey(columnToUpdate)) {
			Object value = fieldMap.get(columnToUpdate);
			String sqlQuery = String.format("UPDATE User SET %s = '%s' WHERE userId = '%s'", columnToUpdate, value,
					userId);
			try {
				return executeUpdateQuery(sqlQuery);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public List<User> getAll() {

		List<User> users = new ArrayList<>();
		String selectAllSqlQuery = "SELECT * FROM user";
		try {
			return executeGetAllQuery(selectAllSqlQuery);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
