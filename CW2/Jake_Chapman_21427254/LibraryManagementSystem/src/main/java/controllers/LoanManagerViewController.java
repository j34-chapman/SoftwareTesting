package controllers;

import daos.BookDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import daos.LoanDAO;
import models.Book;
import models.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



/**
 * Controller for the LoanManagerView.
 * Handles interactions related to managing loans in the library system.
 */
public class LoanManagerViewController {

    @FXML
    private TableView<Loan> loansTable;
    @FXML
    private TableColumn<Loan, Integer> loanIDColumn;
    @FXML
    private TableColumn<Loan, Integer> memberIDColumn;
    @FXML
    private TableColumn<Loan, Integer> bookIDColumn;
    @FXML
    private TableColumn<Loan, String> loanDateColumn;
    @FXML
    private TableColumn<Loan, String> dueDateColumn;
    @FXML
    private TableColumn<Loan, String> returnDateColumn;
    @FXML
    private TableColumn<Loan, String> fineAmountColumn;
    @FXML
    private Button returnBookButton;

    private LoanDAO loanDAO;

    /**
     * Initializes the controller.
     * Called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        loanDAO = new LoanDAO();
        configureTableColumns();
        loadLoans();
    }

    /**
     * Configures the table columns to bind them to the appropriate Loan properties.
     */
    private void configureTableColumns() {
        loanIDColumn.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        fineAmountColumn.setCellValueFactory(new PropertyValueFactory<>("fineAmount"));
    }

    /**
     * Fetches loans from the database and populates the TableView.
     */
    private void loadLoans() {
        List<Loan> loans = loanDAO.getAllLoans();
        ObservableList<Loan> observableLoans = FXCollections.observableArrayList(loans);
        loansTable.setItems(observableLoans);
    }

    /**
     * Handles the action of the return book button.
     * Opens the ReturnBookDialog for the selected loan.
     */
    @FXML
    private void handleReturnBookAction() {
        Loan selectedLoan = loansTable.getSelectionModel().getSelectedItem();
        int bookID = selectedLoan.getBookId(); // Method to get the book ID from the UI
        int memberID = selectedLoan.getMemberId(); // Method to get the member ID from the UI
        BookDAO bookDAO = new BookDAO();
        Book selectedBook = bookDAO.getBook(bookID);
        LocalDate dueDate = selectedLoan.getDueDate(); // Method to fetch the due date from the database

        if (selectedLoan != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReturnBookDialog.fxml"));
                VBox page = loader.load();

                // Create the dialog Stage
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Return Book");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Pass the selected loan to the dialog controller
                ReturnBookDialogController controller = loader.getController();

                controller.setSelectedLoan(selectedLoan);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();

                // Refresh the loans table after the operation
                loadLoans();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

