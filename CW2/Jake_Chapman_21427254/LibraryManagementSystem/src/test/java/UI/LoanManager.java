package UI;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Book;
import models.Loan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoanManager extends ApplicationTest {

    {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/ReturnBookUX.csv")
    public void returnBookMethod(int loanID, int memberID, int bookID, String loanDateStr, String returnDateStr) {

        moveTo("Loan Manager").clickOn("Loan Manager");

        // Record the initial size of the loan table
        TableView<Loan> loanTable = lookup("#loansTable").queryAs(TableView.class);
        int initialSize = loanTable.getItems().size();

        //Click on book with bookID 1 in the loan table in the #bookIDColumn
        moveTo("#bookIDColumn").clickOn("1");


        //Click on return book button
        moveTo("Return Book").clickOn("Return Book");

        //Click on confirm

        //Enter loan ID
        doubleClickOn("#loanIdField").eraseText(15).write(String.valueOf(loanID));
        doubleClickOn("#memberIdField").eraseText(15).write(String.valueOf(memberID));
        doubleClickOn("#bookIdField").eraseText(15).write(String.valueOf(bookID));

        //Enter loan date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate loanDate = LocalDate.parse(loanDateStr, formatter);

        DatePicker loanDateField = lookup("#loanDateField").queryAs(DatePicker.class);
        interact(() -> loanDateField.setValue(loanDate));

        //Enter return date
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate returnDate = LocalDate.parse(returnDateStr, formatter2);

        DatePicker returnDateField = lookup("#returnDateField").queryAs(DatePicker.class);
        interact(() -> returnDateField.setValue(returnDate));

        moveTo("Confirm").clickOn("Confirm");

        // Assert the availability status in the Book Manager page
        moveTo("Book Manager").clickOn("Book Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        TableView<Book> booksTable = lookup("#booksTable").queryAs(TableView.class);
        boolean isBookAvailable = booksTable.getItems().stream()
                .filter(book -> book.getBookID() == bookID)
                .anyMatch(book -> book.getAvailabilityStatus().equals("Available"));
        assertTrue(isBookAvailable, "Book with ID " + bookID + " should be available");

        // Go back to the Loan Manager page
        moveTo("Loan Manager").clickOn("Loan Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the book ID is not present in the list anymore - it doesnt past this assertion
        assertFalse(loanTable.getItems().stream().anyMatch(loan -> loan.getBookId() == bookID),
               "Book with ID " + bookID + " should not be present in the loan table");







    }





}