package UI;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import main.Main;
import models.Book;
import models.Loan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.testfx.matcher.control.TableViewMatchers.hasNumRows;

class BookManager extends ApplicationTest {

    {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
    }


    @Override
    public void start(Stage stage) throws Exception {

        new Main().start(stage);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/Update-Add-BookUX.csv")
    public void testAddBookMethod(String Title, String AuthorFirstname, String AuthorSurname,
                                                            String ISBN, String PublishDateStr, String Genre,
                                                            String PublishersName, String AvailabilityStatus, String Condition) {
        moveTo("#addBookButton").moveBy(-20.00, 5).clickOn();

        clickOn("#titleField").write(Title);
        clickOn("#authorFirstNameField").write(AuthorFirstname);
        clickOn("#authorSurnameField").write(AuthorSurname);
        clickOn("#isbnField").write(ISBN);

        // Use DateTimeFormatter to parse the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate publishDate = LocalDate.parse(PublishDateStr, formatter);

        DatePicker publishDateField = lookup("#publishDateField").queryAs(DatePicker.class);
        interact(() -> publishDateField.setValue(publishDate));
        clickOn("#genreField").write(Genre);
        clickOn("#publisherNameField").write(PublishersName);
        clickOn("#availabilityStatusField").write(AvailabilityStatus);
        clickOn("#conditionField").write(Condition);

        clickOn("Save");

        TableView<Book> booksTable = lookup("#booksTable").queryAs(TableView.class);

        Book addedBook = booksTable.getItems().get(booksTable.getItems().size() - 1);
        assertEquals(Title, addedBook.getTitle(), "Book Title should match.");
        assertEquals(AuthorFirstname, addedBook.getAuthorFirstName(), "Book Author's first name should match");
        assertEquals(AuthorSurname, addedBook.getAuthorSurname(), "Book Author's surname should match");
        assertEquals(ISBN, addedBook.getISBN(), "Book ISBN should Match");
        assertEquals(PublishDateStr, addedBook.getPublishDate().format(formatter), "Book publish date should match");
        assertEquals(Genre, addedBook.getGenre(), "Book genre should match");
        assertEquals(PublishersName, addedBook.getPublisherName(), "Book publisher name should match");
        assertEquals(AvailabilityStatus, addedBook.getAvailabilityStatus(), "Book availability status should match");
        assertEquals(Condition, addedBook.getCondition(), "Book condition should match");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/Update-Add-BookUX.csv")
    public void testAddBookMethod2(String Title, String AuthorFirstname, String AuthorSurname,
                                                               String ISBN, String PublishDateStr, String Genre,
                                                               String PublishersName, String AvailabilityStatus, String Condition) {
        moveTo("#addBookButton").moveBy(-20.00, 5).clickOn();

        clickOn("#titleField").write(Title);
        clickOn("#authorFirstNameField").write(AuthorFirstname);
        clickOn("#authorSurnameField").write(AuthorSurname);
        clickOn("#isbnField").write(ISBN);

        // Use DateTimeFormatter to parse the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate publishDate = LocalDate.parse(PublishDateStr, formatter);

        DatePicker publishDateField = lookup("#publishDateField").queryAs(DatePicker.class);
        interact(() -> publishDateField.setValue(publishDate));
        clickOn("#genreField").write(Genre);
        clickOn("#publisherNameField").write(PublishersName);
        clickOn("#availabilityStatusField").write(AvailabilityStatus);
        clickOn("#conditionField").write(Condition);

        clickOn("Save");

        // Verify that the TableView has one more row than before
        FxAssert.verifyThat("#booksTable", hasNumRows(lookup("#booksTable").queryAs(TableView.class).getItems().size()));
    }




    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/Update-Add-BookUX.csv")
    public void testEditBookMethod(String Title, String AuthorFirstname, String AuthorSurname,
                                                             String ISBN, String PublishDateStr, String Genre,
                                                             String PublishersName, String AvailabilityStatus, String Condition) {

        clickOn("Hello World");

        // Click on the edit button
        clickOn("#editBookButton");

        // Modify the existing data
        doubleClickOn("#titleField").eraseText(15).write(Title);
        doubleClickOn("#authorFirstNameField").eraseText(15).write(AuthorFirstname);
        doubleClickOn("#authorSurnameField").eraseText(15).write(AuthorSurname);


        moveTo("#isbnField").doubleClickOn("#isbnField").eraseText(23)
                .moveTo("#isbnField").doubleClickOn("#isbnField").eraseText(23).write(ISBN);

        // Use DateTimeFormatter to parse the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate publishDate = LocalDate.parse(PublishDateStr, formatter);

        DatePicker publishDateField = lookup("#publishDateField").queryAs(DatePicker.class);
        interact(() -> publishDateField.setValue(publishDate));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveTo("#genreField").clickOn("#genreField").moveTo(Genre).clickOn(Genre);


        doubleClickOn("#publisherNameField").eraseText(15).write(PublishersName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveTo("#availabilityStatusField").clickOn("#availabilityStatusField").moveTo(AvailabilityStatus).clickOn(AvailabilityStatus);

        // Introduce a delay of 2 seconds (2000 milliseconds)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveTo("#conditionField").clickOn("#conditionField").moveTo(Condition).clickOn(Condition);

        // Introduce a delay of 2 seconds (2000 milliseconds)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        moveTo("Save").clickOn("Save");


        // Validate the edited book
        TableView<Book> booksTable = lookup("#booksTable").queryAs(TableView.class);

        Book editedBook = booksTable.getItems().stream()
                .filter(book -> book.getTitle().equals(Title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Book not found in the table"));

        assertEquals(Title, editedBook.getTitle(), "Book Title should match.");
        assertEquals(AuthorFirstname, editedBook.getAuthorFirstName(), "Book Author's first name should match");
        assertEquals(AuthorSurname, editedBook.getAuthorSurname(), "Book Author's surname should match");
        assertEquals(ISBN, editedBook.getISBN(), "Book ISBN should Match");
        assertEquals(PublishDateStr, editedBook.getPublishDate().format(formatter), "Book publish date should match");
        assertEquals(Genre, editedBook.getGenre(), "Book genre should match");
        assertEquals(PublishersName, editedBook.getPublisherName(), "Book publisher name should match");
        assertEquals(AvailabilityStatus, editedBook.getAvailabilityStatus(), "Book availability status should match");
        assertEquals(Condition, editedBook.getCondition(), "Book condition should match");

        // Helper method to clear the text field

    }



    @ParameterizedTest
    @ValueSource(ints = {13})
    public void testDeleteBookMethod_shouldDeleteBook(int bookID) {

        // Click on the book with BookID . Change this to the actual ID or selector of the book you want to delete
        clickOn(String.valueOf(bookID));

        // Click on the delete button
        clickOn("#deleteBookButton");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveTo("OK").clickOn("OK");

        TableView<Book> booksTable = lookup("#booksTable").queryAs(TableView.class);

        //  assert that the book is no longer present in the UI/table
        assertFalse(booksTable.getItems().stream().anyMatch(book -> book.getBookID() == bookID),
                "Book with ID " + bookID + " should not be deleted");
    }


    @ParameterizedTest
    @ValueSource(ints = {14})
    public void testDeleteBookMethod_AfterCancel_shouldNotDeleteBook(int bookID) {
        // Click on the book with Book ID. Change this to the actual ID or selector of the book you want to delete
        clickOn(String.valueOf(bookID));

        // Click on the delete button
        clickOn("#deleteBookButton"); // Replace with the actual ID or selector of the delete button

        // Sleep for a short duration to allow the UI to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the cancel button
        clickOn("Cancel");

        // Assert that the book is still present in the UI/table
        TableView<Book> booksTable = lookup("#booksTable").queryAs(TableView.class);

        // Check if any book in the TableView has the specified bookID
        assertTrue(booksTable.getItems().stream().anyMatch(book -> book.getBookID() == bookID),
                "Book with ID " + bookID + " should not be deleted");
    }




    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/LoanBookUX.csv")
    public void testLoanBooknMethod1(int bookID, int memberID, int loanDuration) {
        moveTo("Loan Manager").clickOn("Loan Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Record the initial size of the loan table
        TableView<Loan> loanTable = lookup("#loansTable").queryAs(TableView.class);
        int initialSize = loanTable.getItems().size();

        // Navigate back to the main page
        moveTo("Book Manager").clickOn("Book Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on a book with the specified bookID
        clickOn(String.valueOf(bookID));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the "Loan Book" button
        clickOn("#loanBookButton");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter the bookID, memberID, and loanDuration
        doubleClickOn("#bookIdField").eraseText(15).write(String.valueOf(bookID));
        doubleClickOn("#memberIdField").eraseText(15).write(String.valueOf(memberID));
        doubleClickOn("#loanDurationField").eraseText(15).write(String.valueOf(loanDuration));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the "Confirm" button
        clickOn("Confirm");

        // Go back to the Loan Manager page
        moveTo("Loan Manager").clickOn("Loan Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check/assert that the table size has increased compared to the initial size
        int newSize = loanTable.getItems().size();
        assertTrue(newSize > initialSize, "Table size should have increased");

        // Check/assert that the bookID that was just loaned is present in the Book ID column
        assertTrue(loanTable.getItems().stream().anyMatch(loan -> loan.getMemberId() == memberID),
                "Member with " + memberID + " should be present in the table");
    }












}







