package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Book;
import models.Loan;
import daos.BookDAO;
import daos.LoanDAO;
import java.time.LocalDate;

public class LoanBookDialogController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private TextField loanDurationField;

    private Stage dialogStage;
    private Book selectedBook;
    private boolean confirmed = false;
    private LoanDAO loanDAO = new LoanDAO();
    private BookDAO bookDAO = new BookDAO();

    // Initialize method if needed
    public void initialize() {
        loanDurationField.setText("14");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
        bookIdField.setText(Integer.toString(selectedBook.getBookID()));
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    private void handleConfirm() {
        if (selectedBook == null) {
            showError("No book selected. Please select a book before attempting to loan.");
            return;
        }

        try {
            int memberId = Integer.parseInt(memberIdField.getText());
            int bookId = selectedBook.getBookID(); // Retrieve the book ID
            int loanDuration = Integer.parseInt(loanDurationField.getText());
            if (bookId < 1) {
                showError("Invalid book ID. Please select a valid book.");
                return;
            }

            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusDays(14); // For example, the due date is 14 days from now

            Loan newLoan = new Loan(memberId, bookId, LocalDate.now(), LocalDate.now().plusDays(loanDuration));

            if (loanDAO.borrowBook(newLoan)) {
                confirmed = true;
                dialogStage.close();
            } else {
                showError("Failed to loan the book. Please try again.");
            }

        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter a numeric Member ID.");
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Loan Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
