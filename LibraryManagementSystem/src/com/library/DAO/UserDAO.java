package com.library.DAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.Model.Gender;
import com.library.Model.Role;
import com.library.Model.User;
import com.library.UserConstant.UserConstants;

 
public class UserDAO {
	
	private Connection connection;
 
	public UserDAO()  {
		try {
			this.connection = DBConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("DB connection not established");
			e.printStackTrace();
		}
	}
 
//	public User getUserByUserName(String username) throws SQLException {
//        User user = null;
//        String query = "SELECT * FROM USER WHERE userName = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setString(1, username);
//            try (ResultSet resultSet = pstmt.executeQuery()) {
//                if (resultSet.next()) {
//                    user = new User();
//                    user.setUserId(resultSet.getString("userId"));
//                    user.setUserName(resultSet.getString("userName"));
//                    user.setEmail(resultSet.getString("email"));
//                    Role role = Role.valueOf(resultSet.getString("role"));
//                    user.setPassword(resultSet.getString("password"));
//                    user.setRole(role);
//                }
//            }
//        }
//        return user;
//    }
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT userId, name, userName, role, gender, dateOfBirth, department, designation, contactNumber, email, address, bookIssueLimit, fine, password FROM user";
	    
	    User user = null;
	    
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	       
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                // Map the result set to a User object
	                user = new User();
	                user.setUserId(rs.getString("userId"));
	                user.setName(rs.getString("name"));
	                user.setUserName(rs.getString("userName"));
	                user.setRole(Role.valueOf(rs.getString("role"))); // Assuming Role is an enum
	                user.setGender(Gender.valueOf(rs.getString("gender"))); // Assuming Gender is an enum
	                user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate()); // Convert SQL Date to LocalDate
	                user.setDepartment(rs.getString("department"));
	                user.setDesignation(rs.getString("designation"));
	                user.setContactNumber(rs.getString("contactNumber"));
	                user.setEmail(rs.getString("email"));
	                user.setAddress(rs.getString("address"));
	                user.setBookIssueLimit(rs.getInt("bookIssueLimit"));
	                user.setFine(rs.getBigDecimal("fine"));
	                user.setPassword(rs.getString("password"));
	                users.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        // Log the exception (replace with proper logging if needed)
	        System.err.println("Error while retrieving user by username: " + e.getMessage());
	    }
	    
	    return users;
	}

	public boolean deleteUser(String userId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM user WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            return stmt.executeUpdate() > 0; // Returns true if a row was deleted
        }
		
	}
	public boolean addUser(User user)
	{
		
			
		String sql = "INSERT INTO user (userId, name, userName, role, gender, dateOfBirth, department, designation, contactNumber, email, address, bookIssueLimit, fine, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	       
		 PreparedStatement ps = null;
		 
			 	try {
			 			ps = connection.prepareStatement(sql);
			 		 	ps.setString(1, user.getUserId());
			            ps.setString(2, user.getName());
			            ps.setString(3, user.getUserName());
			            ps.setString(4, user.getRole().toString());
			            ps.setString(5, user.getGender().toString());
			            ps.setDate(6, java.sql.Date.valueOf(user.getDateOfBirth()));
			            ps.setString(7, user.getDepartment());
			            ps.setString(8, user.getDesignation());
			            ps.setString(9, user.getContactNumber());
			            ps.setString(10, user.getEmail());
			            ps.setString(11, user.getAddress());
			            ps.setInt(12, user.getBookIssueLimit());
			            ps.setBigDecimal(13, user.getFine());
			            ps.setString(14, user.getPassword());

		            ps.executeUpdate();
		            return true;
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.out.println("Unsuccessful");
					return false;
				}
	           
	}

	public User getUserByUserName(String userId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT userId, name, userName, role, gender, dateOfBirth, department, designation, contactNumber, email, address, bookIssueLimit, fine, password FROM user WHERE userName = ?";
	    
	    User user = null;
	    
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setString(1, userId);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Map the result set to a User object
	                user = new User();
	                user.setUserId(rs.getString("userId"));
	                user.setName(rs.getString("name"));
	                user.setUserName(rs.getString("userName"));
	                user.setRole(Role.valueOf(rs.getString("role"))); // Assuming Role is an enum
	                user.setGender(Gender.valueOf(rs.getString("gender"))); // Assuming Gender is an enum
	                user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate()); // Convert SQL Date to LocalDate
	                user.setDepartment(rs.getString("department"));
	                user.setDesignation(rs.getString("designation"));
	                user.setContactNumber(rs.getString("contactNumber"));
	                user.setEmail(rs.getString("email"));
	                user.setAddress(rs.getString("address"));
	                user.setBookIssueLimit(rs.getInt("bookIssueLimit"));
	                user.setFine(rs.getBigDecimal("fine"));
	                user.setPassword(rs.getString("password"));
	            }
	        }
	    } catch (SQLException e) {
	        // Log the exception (replace with proper logging if needed)
	        System.err.println("Error while retrieving user by username: " + e.getMessage());
	    }
	    
	    return user;
		
	}

	public void updateUser(User user, String userId, String columnToUpdate)
			 {
		String updateSQL = String.format("UPDATE User SET %s = ? WHERE userId = ?", columnToUpdate);
 
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
 
			prepareStatementForUpdate(preparedStatement, user, columnToUpdate);
			preparedStatement.executeUpdate();
			System.out.println("Updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not updated");
			e.printStackTrace();
		}
	}
 
	protected void prepareStatementForUpdate(PreparedStatement preparedStatement, User user, String columnToUpdate)
			throws SQLException {
		switch (columnToUpdate) {
		case UserConstants.NAME_COLUMN:
			preparedStatement.setString(1, user.getName());
			break;
		case UserConstants.EMAIL_COLUMN:
			preparedStatement.setString(1, user.getEmail());
			break;
		case UserConstants.USERNAME_COLUMN:
			preparedStatement.setString(1, user.getUserName());
			break;
		case UserConstants.PASSWORD_COLUMN:
			preparedStatement.setString(1, user.getPassword());
			break;
		case UserConstants.DEPARTMENT_COLUMN:
			preparedStatement.setString(1, user.getDepartment());
			break;
		case UserConstants.DESIGNATION_COLUMN:
			preparedStatement.setString(1, user.getDesignation());
			break;
		case UserConstants.CONTACT_NO_COLUMN:
			preparedStatement.setString(1, user.getContactNumber());
			break;
		case UserConstants.ADDRESS_COLUMN:
			preparedStatement.setString(1, user.getAddress());
			break;
		case UserConstants.ROLE_COLUMN:
			preparedStatement.setString(1, user.getRole().name());
			break;
		case UserConstants.FINE_COLUMN:
			preparedStatement.setDouble(1, user.getFine().doubleValue());
		case UserConstants.BOOK_ISSUE_LIMIT_COLUMN:
			preparedStatement.setInt(1,user.getBookIssueLimit());
		default:
			break;
 
		}
		preparedStatement.setString(2, user.getUserId());
	}

	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql = "SELECT userId, name, userName, role, gender, dateOfBirth, department, designation, contactNumber, email, address, bookIssueLimit, fine, password FROM user WHERE userId = ?";
			    
			    User user = null;
			    
			    try (PreparedStatement ps = connection.prepareStatement(sql)) {
			        ps.setString(1, userId);
			        
			        try (ResultSet rs = ps.executeQuery()) {
			            if (rs.next()) {
			                // Map the result set to a User object
			                user = new User();
			                user.setUserId(rs.getString("userId"));
			                user.setName(rs.getString("name"));
			                user.setUserName(rs.getString("userName"));
			                user.setRole(Role.valueOf(rs.getString("role"))); 
			                user.setGender(Gender.valueOf(rs.getString("gender"))); 
			                user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate()); 
			                user.setDepartment(rs.getString("department"));
			                user.setDesignation(rs.getString("designation"));
			                user.setContactNumber(rs.getString("contactNumber"));
			                user.setEmail(rs.getString("email"));
			                user.setAddress(rs.getString("address"));
			                user.setBookIssueLimit(rs.getInt("bookIssueLimit"));
			                user.setFine(rs.getBigDecimal("fine"));
			                user.setPassword(rs.getString("password"));
			            }
			        }
			    } catch (SQLException e) {
			        // Log the exception (replace with proper logging if needed)
			        System.err.println("Error while retrieving user by username: " + e.getMessage());
			    }
			    
			    return user;
	}
}
