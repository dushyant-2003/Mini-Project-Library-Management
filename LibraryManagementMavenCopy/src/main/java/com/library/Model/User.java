package com.library.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class User {
	private String userId;
	private String name;
	private String userName;
	private Role role;
	private Gender gender;
	private LocalDate dateOfBirth;
	private String department;
	private String designation;
	private String contactNumber;
	private String email;
	private String address;
	private int bookIssueLimit;
	private BigDecimal fine;
	private String password;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public User() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBookIssueLimit() {
		return bookIssueLimit;
	}

	public void setBookIssueLimit(int bookIssueLimit) {
		this.bookIssueLimit = bookIssueLimit;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		// TODO Auto-generated method stub
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User(String userId, String name, String userName, Role role, Gender gender, LocalDate dateOfBirth,
			String department, String designation, String contactNumber, String email, String address,
			int bookIssueLimit, BigDecimal fine, String password) {

		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.role = role;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.department = department;
		this.designation = designation;
		this.contactNumber = contactNumber;
		this.email = email;
		this.address = address;
		this.bookIssueLimit = bookIssueLimit;
		this.fine = fine;
		this.password = password;
	}

}
