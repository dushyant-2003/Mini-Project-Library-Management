package com.library.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.library.Model.Book;
import com.library.Model.BookIssued;
import com.library.Model.Role;
import com.library.Model.Status;

public class BookDAO {
	private static final long FINE_PER_DAY = 2;
	private Connection connection;

	public BookDAO() throws ClassNotFoundException, SQLException {
		this.connection = DBConnectionManager.getConnection();
		
	}
	 public boolean addBook(Book book) throws SQLException {
	        String sql = "INSERT INTO book (bookId, name, author, publication, edition, price, shelfLocation, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	            
	        pstmt.setString(1, book.getBookId());
	        pstmt.setString(2, book.getName());
	        pstmt.setString(3, book.getAuthor());
	        pstmt.setString(4, book.getPublication());
	        pstmt.setString(5, book.getEdition());
	        pstmt.setDouble(6, book.getPrice());
	        pstmt.setString(7, book.getShelfLocation());
	        pstmt.setString(8, book.getStatus().name()); 
	            
	        return pstmt.executeUpdate() > 0;
	        }
	 

	

	     public boolean issueBook(String bookId, String userName,String issueId) {
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;

	         try {
	             // 1. Establish a connection to the database

	             // 2. Check the status of the book in the Book table
	     
	             String checkBookStatusQuery = "SELECT status FROM Book WHERE bookId = ?";
	             pstmt = connection.prepareStatement(checkBookStatusQuery);
	             pstmt.setString(1, bookId);
	             rs = pstmt.executeQuery();

	             if (rs.next()) {
	                 String bookStatus = rs.getString("status");

	                 if ("Available".equalsIgnoreCase(bookStatus)) {
	                     // 3. Retrieve userName from User table
	                     String getUserQuery = "SELECT userId,bookIssueLimit FROM User WHERE userName = ?";
	                     pstmt = connection.prepareStatement(getUserQuery);
	                     pstmt.setString(1, userName);
	                     rs = pstmt.executeQuery();

	                     if (rs.next()) {
	                         String userId = rs.getString("userId");
	                         int bookIssueLimit = rs.getInt("bookIssueLimit");
	                         
	                         if(bookIssueLimit == 0) {
	                        	 System.out.println("You cannot issue more than three books. Max limit exceeded");
	                        	 return false;
	                         }
	                         
	                         // 4. Insert the book issue entry into the BookIssued table
	                         String insertBookIssueQuery = "INSERT INTO BookIssued (issueId, userId, bookId, issueDate, deadline, status, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                         pstmt = connection.prepareStatement(insertBookIssueQuery);

	                        
	                         LocalDate issueDate = LocalDate.now();
	                         LocalDate returnDate = issueDate.plusDays(15);

	                         pstmt.setString(1, issueId);
	                         pstmt.setString(2, userId);
	                         pstmt.setString(3, bookId);
	                         pstmt.setDate(4, Date.valueOf(issueDate));
	                         pstmt.setDate(5, Date.valueOf(returnDate));
	                         pstmt.setString(6, "Issued");
	                         pstmt.setDate(7, Date.valueOf(issueDate)); // Assuming createdAt is the issue date

	                         pstmt.executeUpdate();

	                         // 5. Update the status of the book to 'Issued' in the Book table
	                         String updateBookStatusQuery = "UPDATE Book SET status = ? WHERE bookId = ?";
	                         pstmt = connection.prepareStatement(updateBookStatusQuery);
	                         pstmt.setString(1, "Issued");
	                         pstmt.setString(2, bookId);
	                         pstmt.executeUpdate();
	                         
	                         // 6. Decrease the limit bookIssueLimit
	                         String updateBookIssueLimitStatus = "UPDATE User SET bookIssueLimit = ? WHERE userId = ?";
	                         pstmt = connection.prepareStatement(updateBookIssueLimitStatus);
	                         int remainingBookIssueLimit = bookIssueLimit -1;
	                         pstmt.setInt(1, remainingBookIssueLimit);
	                         pstmt.setString(2, userId);
	                         pstmt.executeUpdate();
	                         System.out.println("Book issued successfully to " + userName);
	                         System.out.println("Remaining limit = " + remainingBookIssueLimit);
	                         
	                         return true;
	                     } else {
	                         System.out.println("User not found.");
	                        
	                     }
	                 } else {
	                     System.out.println("Book is not available for issuing.");
	                 }
	             } else {
	                 System.out.println("Book not found.");
	                
	             }

	         } catch (SQLException e) {
	             e.printStackTrace();
	         } finally {
	             // 6. Close all resources
	             try {
	                 if (rs != null) rs.close();
	                 if (pstmt != null) pstmt.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }
			return false;
	     }
//	     public  void returnBook(String bookId, String userId) {
//	         
//	    	 PreparedStatement pstmt = null;
//	         ResultSet rs = null;
//	         try {
//	             
//	             String checkBookIssueQuery = "SELECT issueDate, deadline FROM BookIssued WHERE bookId = ? AND userId = ? AND status = 'Issued'";
//	             pstmt = connection.prepareStatement(checkBookIssueQuery);
//	             pstmt.setString(1, bookId);
//	             pstmt.setString(2, userId);
//	             rs = pstmt.executeQuery();
//
//	             if (rs.next()) {
//	                 LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
//	                 LocalDate returnDate = rs.getDate("returnDate").toLocalDate();
//
//	                 // 3. Calculate the fine
//	                 LocalDate actualReturnDate = LocalDate.now();
//	                 long daysLate = ChronoUnit.DAYS.between(returnDate, actualReturnDate);
//	                 double fine = daysLate > 0 ? daysLate * FINE_PER_DAY : 0;
//	                 
//	                 // 4. Update the BookIssued table to mark the book as returned
//	                 String updateBookIssueQuery = "UPDATE BookIssued SET status = ?, returnDate = ?,  WHERE bookId = ? AND userId = ? AND status = 'Issued'";
//	                 pstmt = connection.prepareStatement(updateBookIssueQuery);
//	                 pstmt.setString(1, "Returned");
//	                 pstmt.setDate(2, Date.valueOf(actualReturnDate));
//	                 pstmt.setString(3, bookId);
//	                 pstmt.setString(4, userId);
//	                 pstmt.executeUpdate();
//
//	                 // 5. Update the Book status to 'Available' in the Book table
//	                 String updateBookStatusQuery = "UPDATE Book SET status = 'Available' WHERE bookId = ?";
//	                 pstmt = connection.prepareStatement(updateBookStatusQuery);
//	                 pstmt.setString(1, bookId);
//	                 pstmt.executeUpdate();
//
//	                 // 6. Display the fine to the user
//	                 if (fine > 0) {
//	                     System.out.println("Book returned successfully. You have a fine of Rs. " + fine + " for returning the book " + daysLate + " days late.");
//	                     
//	                 } else {
//	                     System.out.println("Book returned successfully. No fine incurred.");
//	                 }
//	             } else {
//	                 System.out.println("No record found for the issued book.");
//	             }
//
//	         } catch (SQLException e) {
//	             e.printStackTrace();
//	         } finally {
//	             // 7. Close all resources
//	             try {
//	                 if (rs != null) rs.close();
//	                 if (pstmt != null) pstmt.close();
//	                 
//	             } catch (SQLException e) {
//	                 e.printStackTrace();
//	             }
//	         }
//	     }
	     public String getBookStatus(String bookId) {
	         String status = null;
	         PreparedStatement pstmt = null;
	         try { 
	             pstmt = connection.prepareStatement("SELECT status FROM Book WHERE bookId = ?");

	             pstmt.setString(1, bookId);
	             ResultSet rs = pstmt.executeQuery();

	             if (rs.next()) {
	                 status = rs.getString("status");
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	         return status;
	     }

	     public void updateBookStatus(String bookId, String status) {
	    	 PreparedStatement pstmt = null;
	     
	         try {
	             pstmt = connection.prepareStatement("UPDATE Book SET status = ? WHERE bookId = ?" );

	             pstmt.setString(1, status);
	             pstmt.setString(2, bookId);
	             pstmt.executeUpdate();
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	     }
	     
	     public BookIssued getIssuedBookDetails(String bookId, String userId) {
	         BookIssued bookIssued = null;
	         PreparedStatement pstmt;
	         try {
	             pstmt = connection.prepareStatement("SELECT * FROM BookIssued WHERE bookId = ? AND userId = ? AND status = 'Issued'");

	             pstmt.setString(1, bookId);
	             pstmt.setString(2, userId);
	             ResultSet rs = pstmt.executeQuery();

	             if (rs.next()) {
	                 bookIssued = new BookIssued();
	                 bookIssued.setIssueId(rs.getString("issueId"));
	                 bookIssued.setUserId(rs.getString("userId"));
	                 bookIssued.setBookId(rs.getString("bookId"));
	                 bookIssued.setIssueDate(rs.getDate("issueDate").toLocalDate());
	                 bookIssued.setDeadlineDate(rs.getDate("deadline").toLocalDate());
	                 //bookIssued.setReturnDate(rs.getDate("returnDate").toLocalDate());
	                 Status status = Status.valueOf(rs.getString("status"));
	                 bookIssued.setStatus(status);
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	         return bookIssued;
	     }
	     public void returnBook(String bookId, String userId, LocalDate returnDate, double fine) {
	        
	              PreparedStatement pstmt = null;;
				try {
					pstmt = connection.prepareStatement("UPDATE BookIssued SET status = 'Returned', returnDate = ? WHERE bookId = ? AND userId = ? AND status = 'Issued'");
					pstmt.setDate(1, Date.valueOf(returnDate));
		             
		             pstmt.setString(2, bookId);
		             pstmt.setString(3, userId);
		             pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
	            
	     }
}
