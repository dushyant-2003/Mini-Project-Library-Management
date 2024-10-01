package com.library.model;


import java.time.LocalDate;

public class BookIssued {
	private String issueId;
	private String userId;
	private String bookId;
	
	private LocalDate issueDate;
	private LocalDate deadlineDate;
	private LocalDate returnDate;
	private Status status;
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(LocalDate localDate) {
		this.deadlineDate = localDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public BookIssued(String issueId, String userId, String bookId, LocalDate issueDate, LocalDate deadlineDate, LocalDate returnDate,
			Status status) {
		super();
		this.issueId = issueId;
		this.userId = userId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.deadlineDate = deadlineDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public BookIssued(String issueId, String userId, String bookId, LocalDate issueDate, LocalDate deadlineDate,Status status) {
		super();
		this.issueId = issueId;
		this.userId = userId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.deadlineDate = deadlineDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public BookIssued() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
        return String.format("%-10s %-15s %-15s %-15s %-10s", 
                              bookId, issueDate, deadlineDate, returnDate, status);
    }
	
	
	
}
