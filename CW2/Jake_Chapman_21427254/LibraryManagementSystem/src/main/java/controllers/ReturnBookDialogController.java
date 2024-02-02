package controllers;

import daos.BookDAO;
import daos.LoanDAO;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Fine;
import models.Loan;

import java.time.LocalDate;

public class ReturnBookDialogController {

    @FXML
    private TextField loanIdField;
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private DatePicker loanDateField;
    @FXML
    private DatePicker returnDateField;
    @FXML
    private Button confirmButton;

    private Loan selectedLoan;

    private LoanDAO loanDAO;
    private DatabaseHandler dbHandler;

    public interface OnBookReturnedCallback {
        void onBookReturned();
    }

    private OnBookReturnedCallback bookReturnedCallback;

    public ReturnBookDialogController() {
        loanDAO = new LoanDAO();
        dbHandler = new DatabaseHandler(); // Assumes you have a default constructor that sets up connection details
    }

    @FXML
    public void initialize() {

    }

    public void setLoan(Loan loan) {
        this.selectedLoan = loan;
        loanIdField.setText(String.valueOf(loan.getLoanId()));
        memberIdField.setText(String.valueOf(loan.getMemberId()));
        bookIdField.setText(String.valueOf(loan.getBookId()));
        loanDateField.setValue(loan.getLoanDate());
        returnDateField.setValue(loan.getReturnDate());
    }

    public void setOnBookReturnedCallback(OnBookReturnedCallback callback) {
        this.bookReturnedCallback = callback;
    }

    @FXML
    private void handleConfirmReturn() {
        if (selectedLoan != null) {
            if (loanDAO.returnBook(selectedLoan.getLoanId())) {
                BookDAO bookDAO = new BookDAO();
                bookDAO.updateBookAvailabilityStatus(selectedLoan.getBookId(), "Available");

                // Close the dialog after the operations
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();

                // Notify that the book has been returned
                if (bookReturnedCallback != null) {
                    bookReturnedCallback.onBookReturned();
                }
            }
        }
    }

    public void setSelectedLoan(Loan loan) {
        this.selectedLoan = loan;

        if (loan != null) {
            // Populate the dialog fields with the loan details
            loanIdField.setText(Integer.toString(loan.getLoanId()));
            memberIdField.setText(Integer.toString(loan.getMemberId()));
            bookIdField.setText(Integer.toString(loan.getBookId()));
            loanDateField.setValue(loan.getLoanDate());
            returnDateField.setValue(LocalDate.now());  // Set the default return date to the current date
        } else {
            // Handle the case where the loan is null (this shouldn't typically happen)
            System.err.println("Error: No loan provided to the ReturnBookDialog.");
        }
    }

}
