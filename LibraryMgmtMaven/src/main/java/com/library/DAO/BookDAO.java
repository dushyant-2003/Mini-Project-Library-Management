package com.library.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library.Model.Book;
import com.library.Model.BookIssued;
import com.library.Model.IssuedBookDetails;
import com.library.Model.Status;

public class BookDAO {

	private Connection connection;

	public BookDAO() {
		try {
			this.connection = DBConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public boolean issueBook(BookIssued bookIssued) {

		PreparedStatement pstmt = null;
		try {
			String insertBookIssueQuery = "INSERT INTO BookIssued (issueId, userId, bookId, issueDate, deadline, status) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(insertBookIssueQuery);
			pstmt.setString(1, bookIssued.getIssueId());
			pstmt.setString(2, bookIssued.getUserId());
			pstmt.setString(3, bookIssued.getBookId());
			pstmt.setDate(4, Date.valueOf(bookIssued.getIssueDate()));
			pstmt.setDate(5, Date.valueOf(bookIssued.getDeadlineDate()));
			pstmt.setString(6, bookIssued.getStatus().toString());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public void updateBookStatus(String bookId, String status) {
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("UPDATE Book SET status = ? WHERE bookId = ?");

			pstmt.setString(1, status);
			pstmt.setString(2, bookId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void returnBook(String bookId, String userId, LocalDate returnDate) {

		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(
					"UPDATE BookIssued SET status = 'Returned', returnDate = ? WHERE bookId = ? AND userId = ? AND status = 'Issued'");
			pstmt.setDate(1, Date.valueOf(returnDate));
			pstmt.setString(2, bookId);
			pstmt.setString(3, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<BookIssued> getIssuedBookDetailsByUserId(String userId,String bookId) {
		// TODO Auto-generated method stub
		List<BookIssued> booksIssuedList = new ArrayList<>();
		PreparedStatement pstmt;
		BookIssued bookIssued;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM BookIssued where userId = ? AND bookId = ? AND status = 'Issued'");

			pstmt.setString(1, userId);
			pstmt.setString(2, bookId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bookIssued = new BookIssued();
				bookIssued.setIssueId(rs.getString("issueId"));
				bookIssued.setUserId(rs.getString("userId"));
				bookIssued.setBookId(rs.getString("bookId"));
				bookIssued.setIssueDate(rs.getDate("issueDate").toLocalDate());
				bookIssued.setDeadlineDate(rs.getDate("deadline").toLocalDate());
				// bookIssued.setReturnDate(rs.getDate("returnDate").toLocalDate());
				Status status = Status.valueOf(rs.getString("status"));
				bookIssued.setStatus(status);
				booksIssuedList.add(bookIssued);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return booksIssuedList;

	}

	public List<IssuedBookDetails> getIssuedBook(String userId) {
		// TODO Auto-generated method stub
		String GET_ISSUED_BOOKS_QUERY = String.format(
				"SELECT u.userId, u.userName AS userName,u.name as name,  u.email, b.name AS bookName, b.author, b.price, bi.issueDate, \r\n"
						+ "        DATE_ADD(bi.issueDate, INTERVAL 15 DAY) AS deadline \r\n"
						+ "        FROM BookIssued bi \r\n" + "        JOIN User u ON bi.userId = u.userId  \r\n"
						+ "        JOIN Book b ON bi.bookId = b.bookId \r\n"
						+ "        WHERE bi.status = 'Issued' and u.userId = '%s'",
				userId);
		List<IssuedBookDetails> issuedBooks = new ArrayList<>();

		try (PreparedStatement pstmt = connection.prepareStatement(GET_ISSUED_BOOKS_QUERY);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				IssuedBookDetails details = new IssuedBookDetails();
				details.setUserId(rs.getString("userId"));
				details.setUserName(rs.getString("userName"));
				details.setIssuerName(rs.getString("name"));
				details.setEmail(rs.getString("email"));
				details.setBookName(rs.getString("bookName"));
				details.setAuthor(rs.getString("author"));
				details.setPrice(rs.getDouble("price"));
				details.setIssueDate(rs.getDate("issueDate").toLocalDate());
				details.setDeadline(rs.getDate("deadline").toLocalDate());
				issuedBooks.add(details);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return issuedBooks;
	}

	public List<IssuedBookDetails> getAllIssuedBooks() {
		// TODO Auto-generated method stub
		String GET_ALL_ISSUED_BOOKS_QUERY = String.format(
				"SELECT u.userId, u.userName AS userName,u.name as name,  u.email, b.bookId, b.name AS bookName, b.author, b.price, bi.issueDate, \r\n"
						+ "        DATE_ADD(bi.issueDate, INTERVAL 15 DAY) AS deadline \r\n"
						+ "        FROM BookIssued bi \r\n" + "        JOIN User u ON bi.userId = u.userId  \r\n"
						+ "        JOIN Book b ON bi.bookId = b.bookId \r\n" + "        WHERE bi.status = 'Issued'");
		List<IssuedBookDetails> issuedBooks = new ArrayList<>();

		try (PreparedStatement pstmt = connection.prepareStatement(GET_ALL_ISSUED_BOOKS_QUERY);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				IssuedBookDetails details = new IssuedBookDetails();
				details.setUserId(rs.getString("userId"));
				details.setUserName(rs.getString("userName"));
				details.setBookId(rs.getString("bookId"));
				details.setIssuerName(rs.getString("name"));
				details.setEmail(rs.getString("email"));
				details.setBookName(rs.getString("bookName"));
				details.setAuthor(rs.getString("author"));
				details.setPrice(rs.getDouble("price"));
				details.setIssueDate(rs.getDate("issueDate").toLocalDate());
				details.setDeadline(rs.getDate("deadline").toLocalDate());
				issuedBooks.add(details);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return issuedBooks;
	}

	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();

		String GET_ALL_AVAILABLE_BOOKS_QUERY = "SELECT * FROM book WHERE status = 'AVAILABLE'";
		try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_AVAILABLE_BOOKS_QUERY)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String bookId = resultSet.getString("bookId");
				String name = resultSet.getString("name");
				String author = resultSet.getString("author");
				String publication = resultSet.getString("publication");
				String edition = resultSet.getString("edition");
				double price = resultSet.getDouble("price");
				String shelfLocation = resultSet.getString("shelfLocation");
				Status status = Status.valueOf(resultSet.getString("status"));

				Book book = new Book(bookId, name, author, publication, edition, price, shelfLocation, status);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

}
