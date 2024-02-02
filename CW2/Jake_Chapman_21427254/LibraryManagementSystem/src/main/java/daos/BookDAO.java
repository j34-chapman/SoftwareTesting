package daos;

import database.DatabaseHandler;
import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The BookDAO class provides CRUD operations for books in the SQLite database.
 */
public class BookDAO {

    /** Database connection handler. */
    private final DatabaseHandler dbHandler;

    /**
     * Default constructor that initializes the database handler.
     */
    public BookDAO() {
        dbHandler = new DatabaseHandler();
    }


    /**
     * Inserts a new book into the database.
     *
     * @param book The book to be added.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean addBook(Book book) {
        String query = "INSERT INTO Books (Title, AuthorFirstName, AuthorSurname, ISBN, PublishDate, Genre, PublisherName, AvailabilityStatus, Condition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthorFirstName());
            pstmt.setString(3, book.getAuthorSurname());
            pstmt.setString(4, book.getISBN());

            // Convert the LocalDate to java.sql.Date
            java.sql.Date publishDate = java.sql.Date.valueOf(book.getPublishDate());
            pstmt.setDate(5, publishDate);

            pstmt.setString(6, book.getGenre());
            pstmt.setString(7, book.getPublisherName());
            pstmt.setString(8, book.getAvailabilityStatus());
            pstmt.setString(9, book.getCondition());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    public boolean updateBookAvailabilityStatus(int bookId, String status) {
        String query = "UPDATE Books SET availabilityStatus = ? WHERE bookId = ?";

        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, bookId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Retrieves a book from the database by its ID.
     *
     * @param bookID The ID of the book to be retrieved.
     * @return A Book object or null if not found.
     */
    public Book getBook(int bookID) {
        String query = "SELECT * FROM Books WHERE BookID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("AuthorFirstName"),
                        rs.getString("AuthorSurname"),
                        rs.getString("ISBN"),
                        rs.getObject("PublishDate", LocalDate.class),
                        rs.getString("Genre"),
                        rs.getString("PublisherName"),
                        rs.getString("AvailabilityStatus"),
                        rs.getString("Condition")
                );
                System.out.println(book);
                return book;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching book: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list of all books.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("AuthorFirstName"),
                        rs.getString("AuthorSurname"),
                        rs.getString("ISBN"),
                        rs.getObject("PublishDate", LocalDate.class),
                        rs.getString("Genre"),
                        rs.getString("PublisherName"),
                        rs.getString("AvailabilityStatus"),
                        rs.getString("Condition")
                );
                books.add(book);
            }
            return books;

        } catch (SQLException e) {
            System.err.println("Error fetching all books: " + e.getMessage());
        }
        return books;
    }

    /**
     * Updates a book's details in the database.
     *
     * @param book The book with updated details.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean updateBook(Book book) {
        String query = "UPDATE Books SET Title = ?, AuthorFirstName = ?, AuthorSurname = ?, ISBN = ?, PublishDate = ?, Genre = ?, PublisherName = ?, AvailabilityStatus = ?, Condition = ? WHERE BookID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthorFirstName());
            pstmt.setString(3, book.getAuthorSurname());
            pstmt.setString(4, book.getISBN());
            pstmt.setObject(5, book.getPublishDate());
            pstmt.setString(6, book.getGenre());
            pstmt.setString(7, book.getPublisherName());
            pstmt.setString(8, book.getAvailabilityStatus());
            pstmt.setString(9, book.getCondition());
            pstmt.setInt(10, book.getBookID());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a book from the database.
     *
     * @param bookID The ID of the book to be deleted.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean deleteBook(int bookID) {
        String query = "DELETE FROM Books WHERE BookID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookID);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }

}

