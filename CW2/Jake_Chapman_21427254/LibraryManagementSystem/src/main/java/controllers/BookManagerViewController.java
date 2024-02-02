package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import daos.BookDAO;
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Book;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the BookManagerView.
 * Handles interactions related to managing books in the library system.
 */
public class BookManagerViewController {

    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorFirstNameColumn;
    @FXML
    private TableColumn<Book, String> authorSurnameColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> publishDateColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> publisherNameColumn;
    @FXML
    private TableColumn<Book, String> availabilityStatusColumn;
    @FXML
    private TableColumn<Book, String> conditionColumn;
    @FXML
    private Button loanBookButton;  // Make sure you define this Button in FXML as well
    @FXML
    private Button addBookButton;
    @FXML
    private Button editBookButton;
    @FXML
    private Button deleteBookButton;
    private Stage primaryStage;


    private BookDAO bookDAO;

    /**
     * Initializes the controller.
     * Called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        System.out.println("test");
        bookDAO = new BookDAO();
        configureTableColumns();
        loadBooks();
    }

    /**
     * Configures the table columns to bind them to the appropriate Book properties.
     */
    private void configureTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFirstName"));
        authorSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorSurname"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        publisherNameColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        availabilityStatusColumn.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
    }

    /**
     * Fetches books from the database and populates the TableView.
     */
    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
        booksTable.setItems(observableBooks);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Handles the Add Book action.
     * Launches a dialog to add a new book.
     */
    @FXML
    private void handleAddBook() {
        try {
            // Load the fxml file and create a new stage for the add book dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AddBookDialog.fxml"));
            VBox page = loader.load();

            // Create the dialog stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(booksTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the book into the controller.
            BookAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                Book newBook = controller.getNewBook();
                bookDAO.addBook(newBook);
                loadBooks();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the Edit Book action.
     * Launches a dialog to edit the selected book.
     */
    @FXML
    private void handleEditBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookEditDialog.fxml"));
                Parent root = loader.load();

                BookEditDialogController controller = loader.getController();
                controller.setBook(selectedBook);
                controller.initializeData(selectedBook);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Book");
                stage.showAndWait();

                // Refresh the table after editing
                loadBooks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No Book Selected", "Please select a book to edit.");
        }
    }

    /**
     * Handles the Delete Book action.
     * Deletes the selected book after user confirmation.
     */
    @FXML
    private void handleDeleteBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Show confirmation dialog
            if (showConfirmationDialog()) {
                // Delete from the database
                bookDAO.deleteBook(selectedBook.getBookID());

                // Refresh the table
                loadBooks();
            }
        } else {
            // Notify the user to select a book to delete
            showAlert("No Book Selected", "Please select a book to delete.");
        }
    }

    @FXML
    private void handleLoanBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null && "Available".equals(selectedBook.getAvailabilityStatus())) {
            try {
                // Load the fxml file and create a new stage for the loan book dialog
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/LoanBookDialog.fxml"));

                Pane myPane = (Pane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Loan Book");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage); // Assuming primaryStage is the main application window
                Scene scene = new Scene(myPane);
                dialogStage.setScene(scene);

// Set the stage in the controller
                LoanBookDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setSelectedBook(selectedBook);

// Show the dialog and wait until the user closes it
                dialogStage.showAndWait();


                if (controller.isConfirmed()) {
                    // Update the table views
                    loadBooks();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book that is available for loan.");
            alert.showAndWait();
        }
    }


    /**
     * Shows an alert dialog to the user.
     *
     * @param title   The title of the alert.
     * @param content The content message of the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Shows a confirmation dialog to the user.
     *
     * @return true if the user confirms, false otherwise.
     */
    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Book");
        alert.setHeaderText("Are you sure you want to delete this book?");
        alert.setContentText("This action cannot be undone.");

        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }


}
