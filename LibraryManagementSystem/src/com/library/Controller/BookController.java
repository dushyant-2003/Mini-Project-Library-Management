package com.library.Controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import com.library.Model.Book;
import com.library.Model.Status;
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

	public boolean issueBook(String bookId, String userId) {
		// TODO Auto-generated method stub
		
		if(userId == null || bookId == null) {
			System.out.println("Enter both fields");
		} else {
			return bookService.issueBook(bookId, userId);
		}
		return false;
	}

	public void returnBook(String bookId, String userId) {
		if(userId == null || bookId == null) {
			System.out.println("Enter all fields");
		}
		boolean returnedStatus = bookService.returnBook(bookId,userId);
		if(returnedStatus) {
			System.out.println("Book returned successfully");
		}
		else {
			System.out.println("Unsuccess");
		}
	}
	

}
