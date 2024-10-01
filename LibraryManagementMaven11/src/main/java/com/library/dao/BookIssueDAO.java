	package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.constants.IssuedBookConstants;
import com.library.model.IssuedBookDetails;

public class BookIssueDAO extends GenericDAO<IssuedBookDetails> implements InterfaceBookIssueDAO{

	public BookIssueDAO () {
		
	}
	@Override
	protected IssuedBookDetails mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		IssuedBookDetails issuedBookDetails = null;
		issuedBookDetails = new IssuedBookDetails();
		issuedBookDetails.setUserId(resultSet.getString(IssuedBookConstants.ISSUER_ID_COLUMN));
		issuedBookDetails.setUserName(resultSet.getString(IssuedBookConstants.ISSUER_USERNAME_COLUMN));
		issuedBookDetails.setIssuerName(resultSet.getString(IssuedBookConstants.ISSUER_NAME_COLUMN));
		issuedBookDetails.setEmail(resultSet.getString(IssuedBookConstants.EMAIL_COLUMN));
		issuedBookDetails.setBookId(resultSet.getString(IssuedBookConstants.BOOK_ID_COLUMN));
		issuedBookDetails.setAuthor(resultSet.getString(IssuedBookConstants.AUTHOR_COLUMN));
		issuedBookDetails.setBookName(resultSet.getString(IssuedBookConstants.BOOK_NAME_COLUMN));
		issuedBookDetails.setPrice(resultSet.getDouble(IssuedBookConstants.PRICE_COLUMN));
		issuedBookDetails.setIssueDate(resultSet.getDate(IssuedBookConstants.ISSUE_DATE_COLUMN).toLocalDate());
		issuedBookDetails.setDeadline(resultSet.getDate(IssuedBookConstants.DEADLINE_DATE_COLUMN).toLocalDate());
		return issuedBookDetails;
	}
	public List<IssuedBookDetails> getAllIssuedBooks() {
		// TODO Auto-generated method stub
		
		String getAllIssuedBookQuery = String.format(
				"SELECT u.userId, u.userName AS userName,u.name as name,  u.email, b.bookId, b.name AS bookName, b.author, b.price, bi.issueDate, \r\n"
						+ "        DATE_ADD(bi.issueDate, INTERVAL 15 DAY) AS deadline \r\n"
						+ "        FROM BookIssued bi \r\n" + "        JOIN User u ON bi.userId = u.userId  \r\n"
						+ "        JOIN Book b ON bi.bookId = b.bookId \r\n" + "        WHERE bi.status = 'Issued'");
		List<IssuedBookDetails> issuedBooks = new ArrayList<>();

		try {
			return executeGetAllQuery(getAllIssuedBookQuery);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issuedBooks;
	}
	public List<IssuedBookDetails> getIssuedBook(String userId) {
		// TODO Auto-generated method stub
		String getIssuedBookByUserIdSQLQuery = String.format(
				"SELECT u.userId, u.userName AS userName,u.name as name,  u.email,b.bookId, b.name AS bookName, b.author, b.price, bi.issueDate, \r\n"
						+ "        DATE_ADD(bi.issueDate, INTERVAL 15 DAY) AS deadline \r\n"
						+ "        FROM BookIssued bi \r\n" + "        JOIN User u ON bi.userId = u.userId  \r\n"
						+ "        JOIN Book b ON bi.bookId = b.bookId \r\n"
						+ "        WHERE bi.status = 'Issued' and u.userId = '%s'",
				userId);
		List<IssuedBookDetails> issuedBooks = new ArrayList<>();

		try {
			return executeGetAllQuery(getIssuedBookByUserIdSQLQuery);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return issuedBooks;
	}
}
