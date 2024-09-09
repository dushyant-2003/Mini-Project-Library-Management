package com.library.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.library.Constants.StringConstants;
import com.library.Model.Book;
import com.library.Model.BookIssued;
import com.library.Model.IssuedBookDetails;
import com.library.Model.User;
import com.library.Service.BookService;

public class BookController {
	private BookService bookService;
	Scanner scanner = new Scanner(System.in);

	public BookController(BookService bookService) {
		this.bookService = bookService;

	}

	public void addBook(Book book) {
		try {

			// Add the book using the service
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
		
		if(issuer == null || book == null) {
			System.out.println(StringConstants.NULL_VALUE);
		} else {
			return bookService.issueBook(book, issuer);
		}
		return false;
	}

	public void returnBook(IssuedBookDetails book, User user , boolean isBookLost) {
		if (book == null || user == null) {
			System.out.println(StringConstants.NULL_VALUE);
		}
		boolean returnedStatus = bookService.returnBook(book, user, isBookLost);
		if (returnedStatus) {
			System.out.println("Book returned successfully");
		} else {
			System.out.println("Unsuccess");
		}
	}

//	public List<BookIssued> getIssuedBooksByUserName(String userName) {
//
//		// TODO Auto-generated method stub
//		if (userName == null) {
//			System.out.println("Enter all fields");
//			return null;
//		}
//		List<BookIssued> booksIssuedList = bookService.getIssuedBooksByUserName(userName);
//		return booksIssuedList;
//
//	}

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
