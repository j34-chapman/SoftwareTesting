package NonFunctional;

import daos.LoanDAO;
import models.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NFLoanTest extends ApplicationTest {

    LoanDAO loanDAO;

    @BeforeEach
    void setUp() {
        loanDAO = new LoanDAO();
    }

    @Test
    void testResponseTimeForReturnBookMethod() {
        // Replace 1 with the actual Loan ID you want to update
        int loanIdToUpdate = 1;

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        loanDAO.returnBook(loanIdToUpdate);

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;


        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForBorrowBookMethod() {
        // Create a sample loan object
        Loan sampleLoan = new Loan(1,1, LocalDate.now(), LocalDate.now().plusDays(14));

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        loanDAO.borrowBook(sampleLoan);

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;


        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForGetAllLoansMethod() {
        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        loanDAO.getAllLoans();

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;


        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }




}