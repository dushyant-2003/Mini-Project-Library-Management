package com.library.Controller;
 
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.library.Model.Role;
import com.library.Model.User;
import com.library.Service.UserService;
import com.library.ui.MenuUI;
 
public class UserController {
 
	private UserService userService;
	private Scanner scanner = new Scanner(System.in);
 
	public UserController() {
		
	}
	public UserController(UserService userService) {
		this.userService = userService;
		
	}
    




	public boolean authenticateUser(String username, String password, String role) {
		if(username == null || password == null || role == null)
		{
			
		}
		if(!role.toLowerCase().equals("admin")
				 && !role.toLowerCase().equals("staff") && !role.toLowerCase().equals("client"))
		{
			return false;
		}
		return  userService.authenticateUser(username,password,role);
	}





	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list = userService.getAllUsers();
		return list;
		
	}


	public void addUser(User user) {
	
		
		 boolean addStatus = userService.addUser(user);
		 if(addStatus) {
			 System.out.println("Welcome " + user.getName() + " to the library");
		 } else {
			 System.out.println("User not added");
		 }
	}

	public void updateUser() {
		try {
			System.out.print("Enter user ID: ");
			String userId = scanner.nextLine();
 
			User user = userService.getUserById(userId);
			if (user != null) {
				boolean continueUpdating = true;
 
				while (continueUpdating) {
					MenuUI.displayUpdateMenu();
 
					System.out.print("Select an option (1-10): ");
					int choice = scanner.nextInt();
					scanner.nextLine();
 
					String columnToUpdate = "";
 
					switch (choice) {
					case 1:
						columnToUpdate = "name";
						System.out.print("Enter new name: ");
						user.setName(scanner.nextLine());
						break;
					case 2:
						columnToUpdate = "email";
						System.out.print("Enter new email: ");
						user.setEmail(scanner.nextLine());
						break;
					case 3:
						columnToUpdate = "username";
						System.out.print("Enter new username: ");
						user.setUserName(scanner.nextLine());
						break;
					case 4:
						columnToUpdate = "password";
						System.out.print("Enter new password: ");
						user.setPassword(scanner.nextLine());
						break;
					case 5:
						columnToUpdate = "department";
						System.out.print("Enter new department: ");
						user.setDepartment(scanner.nextLine());
						scanner.nextLine(); // Consume newline character
						break;
					case 6:
						columnToUpdate = "designation";
						System.out.print("Enter new designation: ");
						user.setDesignation(scanner.nextLine().toUpperCase());
						break;
					case 7:
						columnToUpdate = "phoneNo";
						System.out.print("Enter new phone number: ");
						user.setContactNumber(scanner.nextLine());
						break;
					case 8:
						columnToUpdate = "address";
						System.out.print("Enter new address: ");
						user.setAddress(scanner.nextLine());
						break;
					case 9:
						columnToUpdate = "role";
						System.out.print("Enter new role (Admin, Staff, Client): ");
						user.setRole(Role.valueOf(scanner.nextLine()));
						break;
					case 10:
						continueUpdating = false;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
					}
					if (!continueUpdating) {
						break;
					}
					
					userService.updateUser(user, userId, columnToUpdate);
					System.out.println("User updated successfully.");
				}
			} else {
				System.out.println("User not found");
			}
		} catch (Exception e) {
			System.out.println("Error updating user: " + e.getMessage());
		}
	}
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		if(userId == null)
		{
			
			return false;
		}
		boolean isDeletedSuccessfully = userService.deleteUser(userId);
		return isDeletedSuccessfully;
	}
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		if(userName == null)
		{
			return null;
		}
		return userService.getUserById(userName);
		}
	
	
}
