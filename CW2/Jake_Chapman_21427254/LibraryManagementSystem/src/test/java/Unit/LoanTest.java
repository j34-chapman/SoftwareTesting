package Unit;

import daos.LoanDAO;
import models.Fine;
import models.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanTest {

    private LoanDAO loanDao;

    @BeforeEach
    void setUp() {
        // Create an instance of LoanDAO
        loanDao = new LoanDAO();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ReturnBook.csv")  // assuming the file is in the resources directory
    void testReturnBook(int loanId) {
        boolean result = loanDao.returnBook(loanId);

        // Assert that the result matches the expected value
        assertTrue(result, "Returning a book was unsuccessful.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BorrowBook.csv")  // assuming the file is in the resources directory
    void testBorrowBook(String memberId, String bookId, String loanDate, String dueDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int parsedMemberId = Integer.parseInt(memberId);
        int parsedBookId = Integer.parseInt(bookId);
        LocalDate parsedLoanDate = LocalDate.parse(loanDate, formatter);
        LocalDate parsedDueDate = LocalDate.parse(dueDate, formatter);


        // Use the CSV data as parameters for creating the Loan instance
        Loan loan = new Loan(parsedMemberId, parsedBookId, parsedLoanDate, parsedDueDate);

        boolean result = loanDao.borrowBook(loan);

        // Assert that the result is true, indicating a successful book borrowing
        assertTrue(result, "Borrowing a book was unsuccessful.");
    }

    @Test
    void testGetAllLoans() {
        // Call the method under test
        List<Loan> result = loanDao.getAllLoans();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Expected at least one fine in the result");
    }
}
