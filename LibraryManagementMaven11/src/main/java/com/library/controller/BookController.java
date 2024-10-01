package com.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.library.constants.StringConstants;
import com.library.model.Book;
import com.library.model.BookIssued;
import com.library.model.IssuedBookDetails;
import com.library.model.User;
import com.library.service.BookService;

public class BookController {
	private BookService bookService;
	Scanner scanner = new Scanner(System.in);

	public BookController(BookService bookService) {
		this.bookService = bookService;

	}

	public void addBook(Book book) {
		try {
			bookService.addBook(book);
			System.out.println("Book added successfully!");

		} catch (SQLException e) {
			System.out.println("Error while adding the book: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input for price.");
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid status.");
		}
	}

	public boolean issueBook(Book book, User issuer) {
		// TODO Auto-generated method stub

		if (issuer == null || book == null) {
			System.out.println(StringConstants.NULL_VALUE);
		} else {
			return bookService.issueBook(book, issuer);
		}
		return false;
	}

	public void returnBook(IssuedBookDetails book, User user, boolean isBookLost) {
		if (book == null || user == null) {
			System.out.println(StringConstants.NULL_VALUE);
		}
		boolean returnedStatus = bookService.returnBook(book, user, isBookLost);
		if (returnedStatus) {

		} else {
			System.out.println("Unsuccess");
		}
	}

	public List<IssuedBookDetails> getIssuedBook(User user) {
		// TODO Auto-generated method stub

		return bookService.getIssuedBook(user);

	}

	public List<IssuedBookDetails> getAllIssuedBook(boolean seeDefaulterList) {
		// TODO Auto-generated method stub
		return bookService.getAllIssuedBooks(seeDefaulterList);
	}

	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookService.getAllBooks();
	}

	public List<IssuedBookDetails> getAllIssuedBookByUserName(String userName) {
		// TODO Auto-generated method stub
		return bookService.getAllIssuedBookByUserName(userName);

	}

}
