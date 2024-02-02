package Intergration;

import daos.BookDAO;
import models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookIntegrationTest {

    BookDAO bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDAO();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BookIntegration.csv")
    public void testAddBookIntegration(String title, String authorFirstName, String authorSurname,
                                       String isbn, String publishDate, String genre,
                                       String publisherName, String availabilityStatus,
                                       String condition) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(publishDate, formatter);

        Book book = new Book(title, authorFirstName, authorSurname, isbn,
                date, genre, publisherName,
                availabilityStatus, condition);

        // Call the method under test
        boolean wasAdded = bookDao.addBook(book);

        // Fetch the book from the database to verify its details
        Book fetchedBook = bookDao.getBook(16);

        // Verify the result
        assertEquals(book.toString(), fetchedBook.toString(), "Details of the added book do not match the fetched book.");
    }

    @Test
    public void testUpdateBookAvailabilityStatusIntegration() {
        // Set up data for the test
        int bookId = 16;
        String newStatus = "On Loan";

        // Call the method under test
        boolean wasUpdated = bookDao.updateBookAvailabilityStatus(bookId, newStatus);

        // Fetch the book from the database to verify its updated status
        Book updatedBook = bookDao.getBook(bookId);

        // Verify the result
        assertTrue(wasUpdated, "Failed to update book availability status.");
        assertEquals(newStatus, updatedBook.getAvailabilityStatus(), "Book availability status not updated.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BookIntegration.csv")
    public void testGetBookIntegration(String title, String authorFirstName, String authorSurname,
                                       String isbn, String publishDate, String genre,
                                       String publisherName, String availabilityStatus,
                                       String condition) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(publishDate, formatter);

        Book book = new Book(title, authorFirstName, authorSurname, isbn,
                date, genre, publisherName,
                availabilityStatus, condition);

        // Call the method under test
        boolean wasAdded = bookDao.addBook(book);

        // Fetch the book from the database to verify its details
        Book fetchedBook = bookDao.getBook(17);

        // Verify the result
        assertEquals(book.toString(), fetchedBook.toString(), "Details of the added book do not match the fetched book.");
    }

    @Test
    void testGetAllBooksIntegration() {
        // Call the method under test
        List<Book> result = bookDao.getAllBooks();

        // Verify the result
        // Change depending on the number of books in the database
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() == 17, "Expected to find (17) books in the result, however there is " + result.size());
    }

    @Test
    void testUpdateBookIntegration() {
        // Create a Book instance with the updated information
        //Can use any Book with any ID youd like to update
        Book updatedBook = bookDao.getBook(5);
        updatedBook.setTitle("Updated Title 1");
        updatedBook.setAuthorFirstName("Updated AuthorFirstName 1");
        updatedBook.setAuthorSurname("Updated AuthorSurname 1");
        updatedBook.setISBN("124567890 1");
        updatedBook.setPublishDate(LocalDate.now());
        updatedBook.setGenre("Updated Genre 1");
        updatedBook.setPublisherName("Updated PublisherName 1");
        updatedBook.setAvailabilityStatus("Updated AvailabilityStatus   1");
        updatedBook.setCondition("Updated Condition 1");



        // Call the method under test
        boolean result = bookDao.updateBook(updatedBook);

        // Verify the result
        assertTrue(result, "Failed to update the book.");

        Book fetchedBook = bookDao.getBook(16);

        assertEquals(updatedBook.toString(), fetchedBook.toString() ,"Updated book does not match fetched book");



    }

    @Test
    void testDeleteBookIntegration() {
        // Get the book to delete
        //Can choose any book youd like to delete
        Book bookToDelete = bookDao.getBook(5);

        // Call the method under test
        boolean result = bookDao.deleteBook(bookToDelete.getBookID());

        // Verify the result
        assertTrue(result, "Failed to delete the book");

        // Fetch the book again to check if it's deleted
        Book deletedBook = bookDao.getBook(bookToDelete.getBookID());

        // Assert that the deleted book is null
        assertNull(deletedBook, "Book was not deleted");
    }






}

