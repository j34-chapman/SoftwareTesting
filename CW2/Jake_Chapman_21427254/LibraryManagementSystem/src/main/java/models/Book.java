package models;

import java.time.LocalDate;

/**
 * Represents a book in the library management system.
 */
public class Book {

    // Attributes based on the database schema for the Books table
    private int bookID;
    private String title;
    private String authorFirstName;
    private String authorSurname;
    private String ISBN;
    private LocalDate publishDate;
    private String genre;
    private String publisherName;
    private String availabilityStatus;
    private String condition;

    /**
     * Constructor to initialize a book with all attributes.
     *
     * @param bookID            Unique ID for the book.
     * @param title             Title of the book.
     * @param authorFirstName   First name of the book's author.
     * @param authorSurname     Surname of the book's author.
     * @param ISBN              International Standard Book Number.
     * @param publishDate       Date of book's publication.
     * @param genre             Genre of the book.
     * @param publisherName     Name of the book's publisher.
     * @param availabilityStatus Current availability status of the book.
     * @param condition         Condition of the book.
     */
    public Book(int bookID, String title, String authorFirstName, String authorSurname, String ISBN,
                LocalDate publishDate, String genre, String publisherName, String availabilityStatus, String condition) {
        this.bookID = bookID;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorSurname = authorSurname;
        this.ISBN = ISBN;
        this.publishDate = publishDate;
        this.genre = genre;
        this.publisherName = publisherName;
        this.availabilityStatus = availabilityStatus;
        this.condition = condition;
    }

    public Book(String title, String authorFirstName, String authorSurname, String isbn, LocalDate publishDate, String genre, String publisherName, String availabilityStatus, String condition) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorSurname = authorSurname;
        this.ISBN = isbn;
        this.publishDate = publishDate;
        this.genre = genre;
        this.publisherName = publisherName;
        this.availabilityStatus = availabilityStatus;
        this.condition = condition;
    }

    public Book() {

    }

    // Getters and Setters

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publishDate=" + publishDate +
                ", genre='" + genre + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}



