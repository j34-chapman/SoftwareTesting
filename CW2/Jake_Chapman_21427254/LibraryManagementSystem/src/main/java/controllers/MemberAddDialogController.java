package controllers;

import daos.MemberDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Book;
import models.Member;

public class MemberAddDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressLine1Field;
    @FXML
    private TextField addressLine2Field;
    @FXML
    private TextField townOrCityField;
    @FXML
    private TextField countyField;
    @FXML
    private TextField postCodeField;


    @FXML
    private DatePicker dateRegisteredPicker;

    private MemberDAO memberDAO;

    private Stage dialogStage;
    private Member newMember;

    private boolean okClicked = false;

    public MemberAddDialogController() {
        memberDAO = new MemberDAO();
    }


    /**
     * Sets the stage for this dialog.
     *
     * @param dialogStage the dialog stage to be set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handleSave() {
        Member member = new Member();
        member.setFirstName(firstNameField.getText());
        member.setLastName(lastNameField.getText());
        member.setEmail(emailField.getText());
        member.setPhone(phoneField.getText());
        member.setAddressLine1(addressLine1Field.getText());
        member.setAddressLine2(addressLine2Field.getText());
        member.setTownOrCity(townOrCityField.getText());
        member.setCounty(countyField.getText());
        member.setPostCode(postCodeField.getText());
        member.setDateRegistered(dateRegisteredPicker.getValue());

        if (memberDAO.addMember(member)) {
            // Provide feedback to user about successful addition
            System.out.println("Member added successfully.");
        } else {
            // Provide feedback to user about unsuccessful addition
            System.out.println("Error occurred while adding member.");
        }

        closeDialog();
    }


    @FXML
    private void handleCancel() {
        closeDialog();
    }

    /**
     * Returns the new book created by the user.
     *
     * @return the newly created Book object
     */
    public Member getNewMember() {
        return newMember;
    }
    /**
     * Returns whether the user clicked the save button.
     *
     * @return true if the user clicked save, false otherwise
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    private void closeDialog() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
