package com.library.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.library.DAO.BookDAO;
import com.library.Model.Book;
import com.library.Model.BookIssued;

public class BookService {
    private BookDAO bookDAO;

//    public BookService() {
//    	
//        try {
//			this.bookDAO = new BookDAO();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Class not found");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Error! Book not added");
//		}
//    }

    public BookService(BookDAO bookDAO) {
		// TODO Auto-generated constructor stub
    	this.bookDAO = bookDAO;
	}

	public boolean addBook(Book book) throws SQLException {
        boolean addBookStatus = bookDAO.addBook(book);
        return addBookStatus;
        
    }
	
	public boolean issueBook(String bookId,String userId) {
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 8;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String issueId = randomString.substring(0, desiredLength);
		return bookDAO.issueBook(bookId, userId,issueId);
	}

//	public boolean returnBook(String userName, String bookId) {
//		// TODO Auto-generated method stub
//		
//		return false;
//	}
	public boolean returnBook(String bookId, String userId) {
        LocalDate currentDate = LocalDate.now();
        BookIssued bookIssued = bookDAO.getIssuedBookDetails(bookId, userId);

        if (bookIssued != null) {
            LocalDate deadLineDate = bookIssued.getDeadlineDate();
            long daysLate = ChronoUnit.DAYS.between(deadLineDate, currentDate);
            double fine = daysLate > 0 ? daysLate * 2.0 : 0;

            bookDAO.returnBook(bookId, userId, currentDate, fine);
            bookDAO.updateBookStatus(bookId, "Available");
           

            if (fine > 0) {
                System.out.println("Book returned with a fine of Rs. " + fine);
            } else {
                System.out.println("Book returned successfully. No fine incurred.");
            }
            
            return true;
        } else {
            System.out.println("No record found for the issued book.");
        }
        return false;
    }

	
}
