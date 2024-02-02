package UI;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Member;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MemberManager extends ApplicationTest {

    {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/Update-Add-MemberUX.csv")
    public void testAddMemberMethod(String FirstName, String LastName, String Email, String PhoneNumber,
                                         String AdressLine1, String AdressLine2, String TownOrCity, String County,
                                         String PostCode, String DateRegisteredStr) {


        //Click member manager tab
        moveTo("Member Manager").clickOn("Member Manager");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOn("#addMemberButton");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOn("#firstNameField").write(FirstName);
        clickOn("#lastNameField").write(LastName);
        clickOn("#emailField").write(Email);
        clickOn("#phoneField").write(PhoneNumber);
        clickOn("#addressLine1Field").write(AdressLine1);
        clickOn("#addressLine2Field").write(AdressLine2);

        clickOn("#townOrCityField").write(TownOrCity);
        clickOn("#countyField").write(County);
        clickOn("#postCodeField").write(PostCode);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateRegistered = LocalDate.parse(DateRegisteredStr, formatter);

        DatePicker dateRegisteredField = lookup("#dateRegisteredPicker").queryAs(DatePicker.class);
        interact(() -> dateRegisteredField.setValue(dateRegistered));


        moveTo("Save").clickOn("Save");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        TableView<Member> membersTable = lookup("#membersTable").queryAs(TableView.class);

        Member addedMember = membersTable.getItems().get(membersTable.getItems().size() - 1);
        assertEquals(FirstName, addedMember.getFirstName(), "Member First Name should match.");
        assertEquals(LastName, addedMember.getLastName(), "Member Last Name should match.");
        assertEquals(Email, addedMember.getEmail(), "Member Email should match.");
        assertEquals(PhoneNumber, addedMember.getPhone(), "Member Phone Number should match.");
        assertEquals(AdressLine1, addedMember.getAddressLine1(), "Member Address Line 1 should match.");
        assertEquals(AdressLine2, addedMember.getAddressLine2(), "Member Address Line 2 should match.");
        assertEquals(TownOrCity, addedMember.getTownOrCity(), "Member Town or City should match.");
        assertEquals(County, addedMember.getCounty(), "Member County should match.");
        assertEquals(PostCode, addedMember.getPostCode(), "Member Post Code should match.");
        assertEquals(DateRegisteredStr, addedMember.getDateRegistered().format(formatter), "Member Date Registered should match.");


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UICSV/Update-Add-MemberUX.csv")
    public void testEditMemberMethod(String FirstName, String LastName, String Email, String PhoneNumber,
                                         String AdressLine1, String AdressLine2, String TownOrCity, String County,
                                         String PostCode, String DateRegisteredStr) {

        //Click member manager tab
        moveTo("Member Manager").clickOn("Member Manager");

        clickOn("Alice");

        // Click on the edit button
        clickOn("#editMemberButton");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Modify the existing data
        doubleClickOn("#firstNameField").eraseText(15).write(FirstName);
        doubleClickOn("#lastNameField").eraseText(15).write(LastName);
        doubleClickOn("#emailField").eraseText(15).write(Email);
        doubleClickOn("#phoneField").eraseText(15).write(PhoneNumber);
        doubleClickOn("#addressLine1Field").eraseText(15).write(AdressLine1);
        doubleClickOn("#addressLine2Field").eraseText(15).write(AdressLine2);

        doubleClickOn("#townOrCityField").eraseText(15).write(TownOrCity);
        doubleClickOn("#countyField").eraseText(15).write(County);
        doubleClickOn("#postCodeField").eraseText(15).write(PostCode);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateRegistered = LocalDate.parse(DateRegisteredStr, formatter);

        DatePicker dateRegisteredField = lookup("#dateRegisteredPicker").queryAs(DatePicker.class);
        interact(() -> dateRegisteredField.setValue(dateRegistered));

        moveTo("Save").clickOn("Save");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TableView<Member> membersTable = lookup("#membersTable").queryAs(TableView.class);


        Member editedMember = membersTable.getItems().stream()
                .filter(member -> member.getFirstName().equals(FirstName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Book not found in the table"));

        assertEquals(FirstName, editedMember.getFirstName(), "Member First Name should match.");
        assertEquals(LastName, editedMember.getLastName(), "Member Last Name should match.");
        assertEquals(Email, editedMember.getEmail(), "Member Email should match.");
        assertEquals(PhoneNumber, editedMember.getPhone(), "Member Phone Number should match.");
        assertEquals(AdressLine1, editedMember.getAddressLine1(), "Member Address Line 1 should match.");
        assertEquals(AdressLine2, editedMember.getAddressLine2(), "Member Address Line 2 should match.");
        assertEquals(TownOrCity, editedMember.getTownOrCity(), "Member Town or City should match.");
        assertEquals(County, editedMember.getCounty(), "Member County should match.");
        assertEquals(PostCode, editedMember.getPostCode(), "Member Post Code should match.");
        assertEquals(DateRegisteredStr, editedMember.getDateRegistered().format(formatter), "Member Date Registered should match.");
        }


    @ParameterizedTest
    @ValueSource(ints = {1})
    public void testDeleteMember_shouldDeleteMember(int memberID) {

        //Click member manager tab
        moveTo("Member Manager").clickOn("Member Manager");

        // Click on the book with BookID . Change this to the actual ID or selector of the member you want to delete
        clickOn(String.valueOf(memberID));

        // Click on the delete button
        clickOn("#deleteMemberButton"); // Replace with the actual ID or selector of the delete button

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveTo("OK").clickOn("OK");

        TableView<Member> membersTable = lookup("#membersTable").queryAs(TableView.class);

        assertFalse(membersTable.getItems().stream().anyMatch(member -> member.getMemberID() == memberID),
                "Member with ID " + memberID + " should be deleted");


    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void testDeleteMemberButtonMethod_AfterCancel_shouldNotDeleteMember(int memberID) {

        //Click member manager tab
        moveTo("Member Manager").clickOn("Member Manager");

        // Click on the book with BookID . Change this to the actual ID or selector of the member you want to delete
        clickOn(String.valueOf(memberID));

        // Click on the delete button
        clickOn("#deleteMemberButton"); // Replace with the actual ID or selector of the delete button

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the cancel button
        moveTo("Cancel").clickOn("Cancel");


        TableView<Member> membersTable = lookup("#membersTable").queryAs(TableView.class);

        assertTrue(membersTable.getItems().stream().anyMatch(member -> member.getMemberID() == memberID),
                "Member with ID " + memberID + " should not be deleted");


    }











}