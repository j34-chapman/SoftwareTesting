package NonFunctional;

import daos.BookDAO;
import models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class NFBookTest extends ApplicationTest {

    BookDAO bookdao;

    @BeforeEach
    void setUp() {
        bookdao = new BookDAO();
    }

    @Test
    void testResponseTimeForGetAllBooksMethod() {
        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        bookdao.getAllBooks();

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;

        // Convert elapsed time to seconds
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;

        // Set a response time threshold (e.g., 2 seconds)
        double responseTimeThreshold = 2.0;

        // Assert that the response time is within the acceptable threshold
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForAddBookMethod() {
        // Create a sample book object


        Book sampleBook = new Book("The Great Gatsby", "Bob","Marley", "9780141182636", LocalDate.of(1925, 4, 10),
                "Classic", "Penguin Books", "AVAILABLE", "GOOD");


        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        bookdao.addBook(sampleBook);

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForDeleteBookMethod() {
        // Replace 1 with the actual Book ID you want to delete
        int bookIdToDelete = 1;

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        bookdao.deleteBook(bookIdToDelete);

        // Record the end time
        long endTime = System.currentTimeMillis();

        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForUpdateBookAvailabilityStatusMethod() {

        // Replace 1 with the actual Book ID you want to update
        int bookIdToUpdate = 1;
        String newAvailabilityStatus = "ON_LOAN";

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test (
        bookdao.updateBookAvailabilityStatus(bookIdToUpdate, newAvailabilityStatus);

        // Record the end time
        long endTime = System.currentTimeMillis();


        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");

    }

    @Test
    void testResponseTimeForGetBookMethod() {

        // Replace 1 with the actual Book ID you want to update
        int bookToGet = 1;

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test (
        bookdao.getBook(bookToGet);

        // Record the end time
        long endTime = System.currentTimeMillis();


        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForUpdateBookMethod() {
        // Create a sample book object
        Book sampleBook = new Book(17,"The Great Gatsby", "Bob","Charley", "1234567890", LocalDate.of(1950, 6, 11),
                "Classic", "Penguin Books", "AVAILABLE", "GOOD");

        // Add the book to the database to get a valid BookID
        bookdao.addBook(sampleBook);

        // Retrieve the book from the database to obtain the BookID make sure its one greater than the last one
        Book bookToUpdate = bookdao.getBook(17);

        // Check if the book exists
        assertNotNull(bookToUpdate, "Book with ID 17 not found");

        // Modify the sample book data with the desired changes
        bookToUpdate.setTitle("Updated Title");
        bookToUpdate.setGenre("Updated Genre");

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        boolean updateResult = bookdao.updateBook(bookToUpdate);
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
        assertTrue(updateResult, "Update operation failed");
        bookdao.deleteBook(17);
    }









}