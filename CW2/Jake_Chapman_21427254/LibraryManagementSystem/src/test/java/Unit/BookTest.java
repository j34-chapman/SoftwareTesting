package Unit;

import daos.BookDAO;
import models.Book;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;


class BookTest {

    BookDAO bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDAO();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/AddBook.csv")
    public void testAddBook(String title, String authorFirstName, String authorSurname,
                            String isbn, String publishDate, String genre,
                            String publisherName, String availabilityStatus,
                            String condition) {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(publishDate, formatter);

            Book book = new Book(title, authorFirstName, authorSurname, isbn,
                    date, genre, publisherName,
                    availabilityStatus, condition);

            boolean wasAdded = bookDao.addBook(book);

            assertTrue(wasAdded, "Failed to add book.");


    }

    @Test
    public void testAddBookWithBookId() {
        int bookId = 1;
        String title = "Sample Title";
        String authorFirstName = "John";
        String authorSurname = "Doe";
        String isbn = "1234567890";
        String publishDate = "20/01/2022";
        String genre = "Fiction";
        String publisherName = "Sample Publisher";
        String availabilityStatus = "Available";
        String condition = "Good";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(publishDate, formatter);

        Book book = new Book(bookId,title, authorFirstName, authorSurname, isbn,
                date, genre, publisherName,
                availabilityStatus, condition);

        // Call the method under test
        boolean wasAdded = bookDao.addBook(book);

        // Verify the result
        assertTrue(wasAdded, "Should not be able to add a book with pre defined bookId.");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteBook.csv")
    void testDeleteBook(int bookIdToDelete) {
        //Act
        // Attempt to delete the book
        boolean DeletionResult = bookDao.deleteBook(bookIdToDelete);

        //Assert
        // Check if the actual deletion works
        assertTrue(DeletionResult ,"Book deletion was unsucessful.");


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UpdateBookAvailabilityStatus.csv")
    void testUpdateBookAvailabilityStatus(int bookId, String status) {

        // Attempt to update the availability status
        boolean UpdateResult = bookDao.updateBookAvailabilityStatus(bookId, status);

        // Check if the update result works
        assertTrue(UpdateResult ,"Book availability update was unsucessful.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GetBook.csv")
    void testGetBook(int bookID) {
        Book retrievedBook = bookDao.getBook(bookID);


            assertNull(retrievedBook, "Book with ID " + bookID + " was not retrieved");
            //assertEquals(bookID, retrievedBook.getBookID(), "Retrieved book has incorrect ID");

    }

    @Test
    void testGetAllBooks() throws SQLException {

        // Call the method under test
        List<Book> result = bookDao.getAllBooks();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Expected at least one book in the result");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UpdateBook.csv")
    public void testUpdateBook(String bookID, String title, String authorFirstName, String authorSurname,
                               String isbn, String publishDate, String genre,
                               String publisherName, String availabilityStatus,
                               String condition) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(publishDate, formatter);

        // Parse bookID to int
        int parsedBookID = Integer.parseInt(bookID);

        Book book = new Book(parsedBookID, title, authorFirstName, authorSurname, isbn,
                date, genre, publisherName, availabilityStatus, condition);

        boolean wasAdded = bookDao.updateBook(book);

        assertTrue(wasAdded, "Failed to add book.");


    }




}