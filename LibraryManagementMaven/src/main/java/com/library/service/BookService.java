package com.library.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import com.library.constants.StringConstants;
import com.library.constants.UserConstants;
import com.library.dao.InterfaceBookDAO;
import com.library.dao.InterfaceBookIssueDAO;
import com.library.dao.InterfaceUserDAO;
import com.library.model.Book;
import com.library.model.BookIssued;
import com.library.model.IssuedBookDetails;
import com.library.model.Status;
import com.library.model.User;
import com.library.util.IdGenerator;
import com.library.util.LoggingUtil;

public class BookService {

    private static final Logger logger = LoggingUtil.getLogger(BookService.class);
    
    private InterfaceBookDAO bookDAO;
    private InterfaceUserDAO userDAO;
    private InterfaceBookIssueDAO bookIssueDAO;
    
    public BookService(InterfaceBookDAO bookDAO, InterfaceUserDAO userDAO, InterfaceBookIssueDAO bookIssueDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
        this.bookIssueDAO = bookIssueDAO;
    }



    public boolean addBook(Book book) throws SQLException {
        logger.log(Level.INFO, "Adding book: {0}", book.getName());
        boolean addBookStatus = bookDAO.addBook(book);
        logger.log(Level.INFO, "Book added status: {0}", addBookStatus);
        return addBookStatus;
    }

    public boolean issueBook(Book book, User issuer) {
        logger.log(Level.INFO, "Attempting to issue book: {0} to user: {1}", new Object[]{book.getName(), issuer.getUserName()});
        
        String issueId = IdGenerator.generateIssueId();
        Status bookStatus = book.getStatus();

        if (!Status.Available.toString().equalsIgnoreCase(bookStatus.toString())) {
            logger.log(Level.WARNING, StringConstants.BOOK_NOT_AVAILABLE);
            return false;
        }

        int bookIssueLimit = issuer.getBookIssueLimit();
        if (bookIssueLimit == 0) {
            logger.log(Level.WARNING, StringConstants.MAX_BOOKS_ISSUED_MSG);
            return false;
        }

        String bookId = book.getBookId();
        String userId = issuer.getUserId();
        LocalDate issueDate = LocalDate.now();
        LocalDate deadLineDate = issueDate.plusDays(15);
        BookIssued bookIssued = new BookIssued(issueId, userId, bookId, issueDate, deadLineDate, Status.Issued);

        bookDAO.updateBookStatus(bookId, Status.Issued.toString());
        issuer.setBookIssueLimit(bookIssueLimit - 1);
        userDAO.updateUser(issuer, userId, UserConstants.BOOK_ISSUE_LIMIT_COLUMN);

        boolean issueStatus = bookDAO.issueBook(bookIssued);
        logger.log(Level.INFO, "Book issued: {0}, Status: {1}", new Object[]{book.getName(), issueStatus});
        return issueStatus;
    }

    public boolean returnBook(IssuedBookDetails book, User user, boolean isBookLost) {
        logger.log(Level.INFO, "Returning book: {0} by user: {1}", new Object[]{book.getBookName(), user.getUserName()});
        
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
            logger.log(Level.WARNING, "Book lost: {0}, Fine imposed: {1}", new Object[]{book.getBookName(), fine});
        } else {
            fine = daysLate > 0 ? daysLate * 2.0 : 0;
            updatedStatus = Status.Available.toString();
        }

        bookDAO.returnBook(bookId, userId, currentDate);
        bookDAO.updateBookStatus(bookId, updatedStatus);

        // Calculate fine
        BigDecimal previousDues = user.getFine();
        BigDecimal newDues = new BigDecimal(fine);
        BigDecimal totalDues = previousDues.add(newDues);
        user.setFine(totalDues);
        userDAO.updateUser(user, userId, UserConstants.FINE_COLUMN);

        // Update book issue limit
        user.setBookIssueLimit(user.getBookIssueLimit() + 1);
        userDAO.updateUser(user, userId, UserConstants.BOOK_ISSUE_LIMIT_COLUMN);

        if (fine > 0) {
            logger.log(Level.INFO, "Book returned with a fine of Rs. {0}", fine);
        } else {
            logger.log(Level.INFO, "Book returned successfully. No fine incurred.");
        }

        return true;
    }

    public List<IssuedBookDetails> getIssuedBook(User user) {
        logger.log(Level.INFO, "Fetching issued books for user: {0}", user.getUserName());
        return bookIssueDAO.getIssuedBook(user.getUserId());
    }

    public List<IssuedBookDetails> getAllIssuedBooks(boolean seeDefaulterList) {
        logger.log(Level.INFO, "Fetching all issued books");
        LocalDate currentDate = LocalDate.now();
        List<IssuedBookDetails> issuedBookDetails = bookIssueDAO.getAllIssuedBooks();
        
        issuedBookDetails.forEach(issuedBook -> {
            if (issuedBook.getDeadline().isBefore(currentDate)) {
                long daysOverdue = ChronoUnit.DAYS.between(issuedBook.getDeadline(), currentDate);
                issuedBook.setFine(2 * daysOverdue);
            }
        });

        if (!seeDefaulterList) {
            return issuedBookDetails;
        }

        return issuedBookDetails.stream()
                .filter(issuedBook -> issuedBook.getDeadline().isBefore(currentDate))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        logger.log(Level.INFO, "Fetching all books");
        return bookDAO.getAllBooks();
    }

    public List<IssuedBookDetails> getAllIssuedBookByUserName(String userName) {
        logger.log(Level.INFO, "Fetching issued books for user name: {0}", userName);
        List<IssuedBookDetails> issuedBookDetails = getAllIssuedBooks(false);
        if (issuedBookDetails.isEmpty()) {
            return issuedBookDetails;
        }
        return issuedBookDetails.stream()
                .filter(issuedBook -> issuedBook.getUserName().equals(userName))
                .collect(Collectors.toList());
    }
}
