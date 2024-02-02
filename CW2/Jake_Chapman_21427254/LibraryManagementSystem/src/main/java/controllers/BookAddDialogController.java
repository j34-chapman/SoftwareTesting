package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Book;

import java.time.LocalDate;

/**
 * Controller for the BookAddDialog view.
 * Handles user interactions related to adding a new book to the library system.
 */
public class BookAddDialogController {

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
    private TextField genreField;
    @FXML
    private TextField publisherNameField;
    @FXML
    private TextField availabilityStatusField;
    @FXML
    private TextField conditionField;

    private Stage dialogStage;
    private Book newBook;
    private boolean okClicked = false;

    /**
     * Sets the stage for this dialog.
     *
     * @param dialogStage the dialog stage to be set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Handles the save action.
     * Validates the input, creates a new Book object, and sets the okClicked flag to true.
     */
    @FXML
    private void handleSave() {

        if (isInputValid()) {
            newBook = new Book();
            newBook.setTitle(titleField.getText());
            newBook.setAuthorFirstName(authorFirstNameField.getText());
            newBook.setAuthorSurname(authorSurnameField.getText());
            newBook.setISBN(isbnField.getText());
            newBook.setPublishDate(publishDateField.getValue());  // Consider using LocalDate if your model supports it.
            newBook.setGenre(genreField.getText());
            newBook.setPublisherName(publisherNameField.getText());
            newBook.setAvailabilityStatus(availabilityStatusField.getText());
            newBook.setCondition(conditionField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Handles the cancel action.
     * Closes the dialog without saving changes.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid, false otherwise
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid title!\n";
        }
        if (authorFirstNameField.getText() == null || authorFirstNameField.getText().length() == 0) {
            errorMessage += "No valid author first name!\n";
        }
        if (authorSurnameField.getText() == null || authorSurnameField.getText().length() == 0) {
            errorMessage += "No valid author surname!\n";
        }
        // Add more validation rules as needed...

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show an error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    /**
     * Returns whether the user clicked the save button.
     *
     * @return true if the user clicked save, false otherwise
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Returns the new book created by the user.
     *
     * @return the newly created Book object
     */
    public Book getNewBook() {
        return newBook;
    }
}
