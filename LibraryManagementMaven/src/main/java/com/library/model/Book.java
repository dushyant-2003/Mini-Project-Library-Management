package com.library.model;

public class Book {
	private String bookId;
	private String name;
	private String author;
	private String publication;
	private String edition;
	private double price;
	private String shelfLocation;
	private Status status;
	
	public Book() {
		
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if (price<0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

		this.price = price;
	}
	public String getShelfLocation() {
		return shelfLocation;
	}
	public void setShelfLocation(String shelfLocation) {
		this.shelfLocation = shelfLocation;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Book(String bookId, String name, String author, String publication, String edition, double price,
			String shelfLocation, Status status) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.publication = publication;
		this.edition = edition;
		this.price = price;
		this.shelfLocation = shelfLocation;
		this.status = status;
	}
	
	
}
