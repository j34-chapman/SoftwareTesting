package Intergration;

import static org.junit.jupiter.api.Assertions.*;

import daos.BookDAO;
import daos.LoanDAO;
import models.Book;
import models.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class LoanIntegrationTest {

     LoanDAO loanDao;
     BookDAO bookDao;


    @BeforeEach
    void setUp() {
        loanDao = new LoanDAO();
        bookDao = new BookDAO();

    }

    @Test
    void testReturnBookIntegration() {
        // Assume there is an existing loan in the database with loanId 1 for testing return
        int loanIdToReturn = 1;

        // Get the initial size of the loans
        int initialLoanSize = loanDao.getAllLoans().size();

        // Call the method under test
        boolean result = loanDao.returnBook(loanIdToReturn);

        // Verify the result
        assertTrue(result, "Returning a book was unsuccessful.");


        // Check if the size has decreased by 1
        assertEquals(initialLoanSize - 1, loanDao.getAllLoans().size(), "Expected one less loan in the result");
    }



    @Test
    void testBorrowBookIntegration() {
        // Create a Loan object for testing
        Loan loan = new Loan(4, 1, LocalDate.now(), LocalDate.now().plusDays(14));

        // Assume there is an existing book in the database with bookId 1 for testing borrowing
        int loanSize = loanDao.getAllLoans().size();

        // Call the method under test
        boolean result = loanDao.borrowBook(loan);

        // Verify the result
        assertTrue(result, "Borrowing a book was unsuccessful.");
        assertEquals(loanSize + 1, loanDao.getAllLoans().size(), "Expected one more loan in the result");

        // Fetch the book from the database to verify its availability status
        Book borrowedBook = bookDao.getBook(loan.getBookId());

        // Verify the availability status of the borrowed book
        assertEquals("On Loan", borrowedBook.getAvailabilityStatus(), "Availability status of the borrowed book is not 'On Loan'");
    }


    @Test
    void testGetAllLoansIntegration() {
        // Call the method under test
        List<Loan> result = loanDao.getAllLoans();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() == 9, "Expected at least 9 loans in the result, however there is " + result.size());
    }

    // Remember to close the connection in an @AfterEach or in the test methods
    // depending on your application's architecture and connection management.
}
