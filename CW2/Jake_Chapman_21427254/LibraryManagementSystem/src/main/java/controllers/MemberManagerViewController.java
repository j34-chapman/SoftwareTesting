package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Member;
import daos.MemberDAO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MemberManagerViewController {

    @FXML
    private TableView<Member> membersTable;

    @FXML
    private TableColumn<Member, Integer> idColumn;
    @FXML
    private TableColumn<Member, String> firstNameColumn;
    @FXML
    private TableColumn<Member, String> lastNameColumn;
    @FXML
    private TableColumn<Member, String> emailColumn;
    @FXML
    private TableColumn<Member, String> phoneColumn;
    @FXML
    private TableColumn<Member, String> addressLine1Column;
    @FXML
    private TableColumn<Member, String> addressLine2Column;
    @FXML
    private TableColumn<Member, String> townOrCityColumn;
    @FXML
    private TableColumn<Member, String> countyColumn;
    @FXML
    private TableColumn<Member, String> postCodeColumn;
    @FXML
    private TableColumn<Member, String> dateRegisteredColumn;

    private MemberDAO memberDAO;


    @FXML
    public void initialize() {
        memberDAO = new MemberDAO();

        // Set up the columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressLine1Column.setCellValueFactory(new PropertyValueFactory<>("addressLine1"));
        addressLine2Column.setCellValueFactory(new PropertyValueFactory<>("addressLine2"));
        townOrCityColumn.setCellValueFactory(new PropertyValueFactory<>("townOrCity"));
        countyColumn.setCellValueFactory(new PropertyValueFactory<>("county"));
        postCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        dateRegisteredColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        // Load data (assuming a method loadData() exists to get members from a database or another source)
        loadData();
    }

    private void loadData() {
        List<Member> allMembers = memberDAO.getAllMembers();
        membersTable.getItems().setAll(allMembers);
    }

    @FXML
    private void handleAddMember() {

            try {
                // Load the fxml file and create a new stage for the add book dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/AddMemberDialog.fxml"));
                GridPane page = (GridPane) loader.load();

                // Create the dialog stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Add Book");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(membersTable.getScene().getWindow());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the book into the controller.
                MemberAddDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);

                // Show the dialog and wait until the user closes it.
                dialogStage.showAndWait();

                if (controller.isOkClicked()) {
                    Member newMember = controller.getNewMember();
                    memberDAO.addMember(newMember);
                    loadData();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void handleEditMember() {
        Member selectedMember = membersTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
            // TODO: Implement the logic to edit the selected member
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a member from the table to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteMember() {
        // Get the selected member from the table
        Member selectedMember = membersTable.getSelectionModel().getSelectedItem();

        if (selectedMember == null) {
            // Show an alert or message indicating no member was selected
            showAlert("No Selection", "No Member Selected", "Please select a member in the table.");
            return;
        }

        // Confirm the deletion
        boolean response = showConfirmation("Confirm Delete", "Are you sure you want to delete this member?", "This action cannot be undone.");
        if (!response) {
            return;
        }

        // Delete the member from the database
        boolean result = memberDAO.deleteMember(selectedMember.getMemberID());

        if (result) {
            // Remove the member from the table view
            membersTable.getItems().remove(selectedMember);
            showAlert("Success", "Member Deleted", "Member was successfully deleted.");
        } else {
            showAlert("Error", "Deletion Failed", "There was an error deleting the member.");
        }

    }

    /**
     * Helper method to show an alert.
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Helper method to show a confirmation dialog.
     */
    private boolean showConfirmation(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
