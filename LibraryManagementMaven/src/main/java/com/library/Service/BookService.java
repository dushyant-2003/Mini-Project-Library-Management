package com.library.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.library.Constants.StringConstants;
import com.library.Constants.UserConstants;
import com.library.DAO.BookDAO;
import com.library.DAO.UserDAO;
import com.library.Model.Book;
import com.library.Model.BookIssued;
import com.library.Model.IssuedBookDetails;
import com.library.Model.Status;
import com.library.Model.User;
import com.library.util.IdGenerator;

public class BookService {

	private BookDAO bookDAO;
	private UserDAO userDAO = new UserDAO();

	public BookService(BookDAO bookDAO) {
		// TODO Auto-generated constructor stub
		this.bookDAO = bookDAO;
	}

	public boolean addBook(Book book) throws SQLException {
		boolean addBookStatus = bookDAO.addBook(book);
		return addBookStatus;

	}

	public boolean issueBook(Book book, User issuer) {

		String issueId = IdGenerator.generateIssueId();

		Status bookStatus = book.getStatus();

		if (!Status.Available.toString().equalsIgnoreCase(bookStatus.toString())) {
			System.out.println(StringConstants.BOOK_NOT_AVAILABLE);
			return false;
		}

		int bookIssueLimit = issuer.getBookIssueLimit();
		if (bookIssueLimit == 0) {
			System.out.println(StringConstants.MAX_BOOKS_ISSUED_MSG);
			return false;
		}

		String bookId = book.getBookId();
		String userId = issuer.getUserId();
		LocalDate issueDate = LocalDate.now();
		LocalDate deadLineDate = issueDate.plusDays(15);
		BookIssued bookIssued = new BookIssued(issueId, userId, bookId, issueDate, deadLineDate,
				Status.valueOf("Issued"));

		bookDAO.updateBookStatus(bookId, Status.Issued.toString());

		int remainingBookIssueLimit = bookIssueLimit - 1;

		issuer.setBookIssueLimit(remainingBookIssueLimit);

		userDAO.updateUser(issuer, userId, UserConstants.BOOK_ISSUE_LIMIT_COLUMN);

		return bookDAO.issueBook(bookIssued);

	}

	public boolean returnBook(IssuedBookDetails book, User user, boolean isBookLost) {
		LocalDate currentDate = LocalDate.now();
		String bookId = book.getBookId();
		String userId = user.getUserId();
		double price = book.getPrice();
		LocalDate deadLineDate = book.getDeadline();
		
		long daysLate = ChronoUnit.DAYS.between(deadLineDate, currentDate);
		double fine = 0;
		String updatedStatus = "";
		if (isBookLost) {
			fine = price;
			updatedStatus = Status.Lost.toString();
		} else {
			fine = daysLate > 0 ? daysLate * 2.0 : 0;
			updatedStatus = Status.Available.toString();
		}

		bookDAO.returnBook(bookId, userId, currentDate);
		bookDAO.updateBookStatus(bookId, updatedStatus);

		// calculate fine
		BigDecimal previousDues = user.getFine();
		BigDecimal newDues = new BigDecimal(fine);
		BigDecimal totalDues = previousDues.add(newDues);
		user.setFine(totalDues);
		String fineColumnName = UserConstants.FINE_COLUMN;
		userDAO.updateUser(user, userId, fineColumnName);
		// update book issue limit
		int currentBookIssueLimit = user.getBookIssueLimit();
		int updatedBookIssueLimit = currentBookIssueLimit + 1;
		user.setBookIssueLimit(updatedBookIssueLimit);
		String bookIssueLimitColumnName = UserConstants.BOOK_ISSUE_LIMIT_COLUMN;
		
		userDAO.updateUser(user, userId, bookIssueLimitColumnName);
		if (fine > 0) {
			System.out.println("Book returned with a fine of Rs. " + fine);
		} else {
			System.out.println("Book returned successfully. No fine incurred.");
		}

		return true;
	}

	public List<IssuedBookDetails> getIssuedBook(User user) {
		// TODO Auto-generated method stub
		String userId = user.getUserId();
		return bookDAO.getIssuedBook(userId);
	}

	public List<IssuedBookDetails> getAllIssuedBooks(boolean seeDefaulterList) {
		// TODO Auto-generated method stub
		LocalDate currentDate = LocalDate.now();
		List<IssuedBookDetails> issuedBookDetails = bookDAO.getAllIssuedBooks();
		issuedBookDetails.forEach(issuedBook -> {
			if (issuedBook.getDeadline().isBefore(currentDate)) {
				long daysOverdue = ChronoUnit.DAYS.between(issuedBook.getDeadline(), currentDate);
				issuedBook.setFine(2 * daysOverdue);
			}
		});
		if (seeDefaulterList == false) {
			return issuedBookDetails;
		}

		return issuedBookDetails.stream().filter(issuedBook -> issuedBook.getDeadline().isBefore(currentDate))
				.collect(Collectors.toList());

	}

	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookDAO.getAllBooks();
	}

	public List<IssuedBookDetails> getAllIssuedBookByUserName(String userName) {
		// TODO Auto-generated method stub
		List<IssuedBookDetails> issuedBookDetails = getAllIssuedBooks(false);
		if (issuedBookDetails.size() == 0) {
			return issuedBookDetails;
		}
		return issuedBookDetails.stream().filter(issuedBook -> issuedBook.getUserName().equals(userName))
				.collect(Collectors.toList());

	}

}
