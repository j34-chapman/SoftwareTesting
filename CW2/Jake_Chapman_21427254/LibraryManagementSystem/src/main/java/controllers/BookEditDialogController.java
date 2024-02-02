package controllers;

import daos.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Book;
import utils.Condition;
import utils.Genres;
import utils.Status;

import java.time.LocalDate;

/**
 * Controller for the BookEditDialog.
 * Handles interactions related to editing book details.
 */
public class BookEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorFirstNameField;
    @FXML
    private TextField authorSurnameField;
    @FXML
    private TextField isbnField;
    @FXML
    private DatePicker publishDateField;
    @FXML
    private ComboBox<Genres> genreField;
    @FXML
    private TextField publisherNameField;
    @FXML
    private ComboBox<Status> availabilityStatusField;
    @FXML
    private ComboBox<Condition> conditionField;

    private BookDAO bookDAO;
    private Book bookToEdit;

    /**
     * Initializes the controller.
     * Called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        bookDAO = new BookDAO();
        genreField.setItems(FXCollections.observableArrayList(Genres.values()));
        availabilityStatusField.setItems(FXCollections.observableArrayList(Status.values()));
        conditionField.setItems(FXCollections.observableArrayList(Condition.values()));

        ObservableList<Genres> genreList = FXCollections.observableArrayList(Genres.values());
        genreField.setItems(genreList);

        ObservableList<Status> statusList = FXCollections.observableArrayList(Status.values());
        availabilityStatusField.setItems(statusList);

        ObservableList<Condition> conditionList = FXCollections.observableArrayList(Condition.values());
        conditionField.setItems(conditionList);
    }

    /**
     * Sets the book to be edited and initializes the fields.
     *
     * @param book The book to be edited.
     */
    public void setBook(Book book) {
        this.bookToEdit = book;

        // Initialize the fields with the book details
        titleField.setText(book.getTitle());
        authorFirstNameField.setText(book.getAuthorFirstName());
        authorSurnameField.setText(book.getAuthorSurname());
        isbnField.setText(book.getISBN());
        publishDateField.setValue(book.getPublishDate());
        try {
            genreField.setValue(Genres.valueOf(book.getGenre().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid genre value from book: " + book.getGenre());
        }
        publisherNameField.setText(book.getPublisherName());
        availabilityStatusField.setValue(Status.valueOf(book.getAvailabilityStatus().toUpperCase()));
        conditionField.setValue(Condition.valueOf(book.getCondition().toUpperCase()));
    }

    /**
     * Initializes the controller with the book to edit.
     */
    public void initializeData(Book book) {
        this.bookToEdit = book;

        titleField.setText(book.getTitle());
        authorFirstNameField.setText(book.getAuthorFirstName());
        authorSurnameField.setText(book.getAuthorSurname());
        isbnField.setText(book.getISBN());
        publishDateField.setValue(book.getPublishDate());
        genreField.setValue(Genres.valueOf(book.getGenre().toUpperCase()));
        publisherNameField.setText(book.getPublisherName());
        availabilityStatusField.setValue(Status.valueOf(book.getAvailabilityStatus().toUpperCase()));
        conditionField.setValue(Condition.valueOf(book.getCondition().toUpperCase()));
    }

    /**
     * Handles the Save action.
     * Updates the book details in the database.
     */
    @FXML
    private void handleSave() {
        bookToEdit.setTitle(titleField.getText());
        bookToEdit.setAuthorFirstName(authorFirstNameField.getText());
        bookToEdit.setAuthorSurname(authorSurnameField.getText());
        bookToEdit.setISBN(isbnField.getText());
        bookToEdit.setPublishDate(publishDateField.getValue());
        bookToEdit.setGenre(genreField.getValue().toString());
        bookToEdit.setPublisherName(publisherNameField.getText());
        bookToEdit.setAvailabilityStatus(availabilityStatusField.getValue().toString());
        bookToEdit.setCondition(conditionField.getValue().toString());

        bookDAO.updateBook(bookToEdit);
        closeDialog();
    }

    /**
     * Handles the Cancel action.
     * Closes the dialog without updating the book details.
     */
    @FXML
    private void handleCancel() {
        closeDialog();
    }

    /**
     * Closes the dialog.
     */
    private void closeDialog() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
