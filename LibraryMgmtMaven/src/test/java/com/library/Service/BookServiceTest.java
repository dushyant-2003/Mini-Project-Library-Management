package com.library.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.library.Constants.UserConstants;
import com.library.DAO.BookDAO;
import com.library.DAO.UserDAO;
import com.library.Model.Book;
import com.library.Model.BookIssued;
import com.library.Model.IssuedBookDetails;
import com.library.Model.Status;
import com.library.Model.User;

class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book();
        when(bookDAO.addBook(book)).thenReturn(true);

        boolean result = bookService.addBook(book);

        assertTrue(result);
        verify(bookDAO, times(1)).addBook(book);
    }

    @Test
    void testIssueBook_Success() {
        Book book = mock(Book.class);
        User user = mock(User.class);
        user.setUserId("123");
        user.setBookIssueLimit(1);
        
        when(book.getStatus()).thenReturn(Status.Available);
        when(user.getBookIssueLimit()).thenReturn(2);
        when(bookDAO.issueBook(any(BookIssued.class))).thenReturn(true);
        when(userDAO.updateUser(user,user.getUserId(),UserConstants.BOOK_ISSUE_LIMIT_COLUMN)).thenReturn(true);
        boolean result = bookService.issueBook(book, user);

        assertTrue(result);
        verify(bookDAO, times(1)).issueBook(any(BookIssued.class));
        verify(userDAO, times(1)).updateUser(user, user.getUserId(), UserConstants.BOOK_ISSUE_LIMIT_COLUMN);
    }

    @Test
    void testIssueBook_BookNotAvailable() {
        Book book = mock(Book.class);
        User user = mock(User.class);

        when(book.getStatus()).thenReturn(Status.Issued);

        boolean result = bookService.issueBook(book, user);

        assertFalse(result);
        verify(bookDAO, never()).issueBook(any(BookIssued.class));
    }

    @Test
    void testReturnBook_NoFine() {
        User user = mock(User.class);
        IssuedBookDetails issuedBook = mock(IssuedBookDetails.class);

        when(issuedBook.getDeadline()).thenReturn(LocalDate.now());
        when(issuedBook.getPrice()).thenReturn(100.0);
        when(user.getFine()).thenReturn(BigDecimal.ZERO);

        boolean result = bookService.returnBook(issuedBook, user, false);

        assertTrue(result);
        verify(bookDAO, times(1)).returnBook(anyString(), anyString(), any(LocalDate.class));
        verify(bookDAO, times(1)).updateBookStatus(anyString(), eq(Status.Available.toString()));
        verify(userDAO, times(1)).updateUser(user, user.getUserId(), UserConstants.FINE_COLUMN);
        verify(userDAO, times(1)).updateUser(user, user.getUserId(), UserConstants.BOOK_ISSUE_LIMIT_COLUMN);
    }

    @Test
    void testReturnBook_WithFine() {
        User user = mock(User.class);
        IssuedBookDetails issuedBook = mock(IssuedBookDetails.class);

        when(issuedBook.getDeadline()).thenReturn(LocalDate.now().minusDays(10));
        when(issuedBook.getPrice()).thenReturn(100.0);
        when(user.getFine()).thenReturn(BigDecimal.ZERO);

        boolean result = bookService.returnBook(issuedBook, user, false);

        assertTrue(result);
        verify(bookDAO, times(1)).returnBook(anyString(), anyString(), any(LocalDate.class));
        verify(bookDAO, times(1)).updateBookStatus(anyString(), eq(Status.Available.toString()));
        verify(userDAO, times(1)).updateUser(user, user.getUserId(), UserConstants.FINE_COLUMN);
        verify(userDAO, times(1)).updateUser(user, user.getUserId(), UserConstants.BOOK_ISSUE_LIMIT_COLUMN);
    }

    @Test
    void testGetIssuedBook() {
        User user = mock(User.class);
        List<IssuedBookDetails> issuedBooks = new ArrayList<>();

        when(bookDAO.getIssuedBook(user.getUserId())).thenReturn(issuedBooks);

        List<IssuedBookDetails> result = bookService.getIssuedBook(user);

        assertEquals(issuedBooks, result);
        verify(bookDAO, times(1)).getIssuedBook(user.getUserId());
    }

    @Test
    void testGetAllIssuedBooks() {
        List<IssuedBookDetails> issuedBooks = new ArrayList<>();
        IssuedBookDetails issuedBook = mock(IssuedBookDetails.class);
        issuedBooks.add(issuedBook);

        when(bookDAO.getAllIssuedBooks()).thenReturn(issuedBooks);

        List<IssuedBookDetails> result = bookService.getAllIssuedBooks(false);

        assertEquals(issuedBooks, result);
        verify(bookDAO, times(1)).getAllIssuedBooks();
    }

    @Test
    void testGetAllIssuedBookByUserName() {
        List<IssuedBookDetails> issuedBooks = new ArrayList<>();
        IssuedBookDetails issuedBook = mock(IssuedBookDetails.class);
        when(issuedBook.getUserName()).thenReturn("username");
        issuedBooks.add(issuedBook);

        when(bookDAO.getAllIssuedBooks()).thenReturn(issuedBooks);

        List<IssuedBookDetails> result = bookService.getAllIssuedBookByUserName("username");

        assertFalse(result.isEmpty());
        verify(bookDAO, times(1)).getAllIssuedBooks();
    }
}
