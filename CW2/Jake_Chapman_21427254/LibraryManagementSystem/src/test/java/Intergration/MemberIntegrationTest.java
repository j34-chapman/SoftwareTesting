package Intergration;

import daos.MemberDAO;
import models.Book;
import models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberIntegrationTest {

    MemberDAO memberDao;

    @BeforeEach
    void setUp() {
        memberDao = new MemberDAO();
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/MemberIntegration.csv")
    public void testAddMember(String FirstName, String LastName, String Email, String Phone, String AddressLine1,
                              String AddressLine2, String TownOrCity, String County, String PostCode,
                              String DateRegistered) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateParsed = LocalDate.parse(DateRegistered, formatter);



        Member member = new Member(FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, TownOrCity, County, PostCode, dateParsed);

        boolean wasAdded = memberDao.addMember(member);

        // Fetch the member from the database to verify its details
        Member fetchedMember = memberDao.getMember(6);

        assertEquals(member.getEmail(), fetchedMember.getEmail(),
                "Details of the added member do not match the fetched member ");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/MemberIntegration.csv")
    public void testGetMember(String FirstName, String LastName, String Email, String Phone, String AddressLine1,
                              String AddressLine2, String TownOrCity, String County, String PostCode,
                              String DateRegistered) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateParsed = LocalDate.parse(DateRegistered, formatter);



        Member member = new Member(FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, TownOrCity, County, PostCode, dateParsed);


        boolean wasAdded = memberDao.addMember(member);


        // Fetch the member from the database to verify its details
        Member fetchedMember = memberDao.getMember(6);

        assertEquals(member.getEmail(), fetchedMember.getEmail(),
                "Details of the added member do not match the fetched member ");

    }

    @Test
    void testGetAllMembersIntegration() {

        // Call the method under test
        List<Member> result = memberDao.getAllMembers();

        // Verify the result
        // Change depending on the number of books in the database
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() == 4, "Expected to find (4) books in the result, however there is " + result.size());
    }


    @Test
    void testUpdateMemberIntegration() {
        // Create a Member instance with the updated information
        // Can use any Member with any ID you'd like to update
        Member updatedMember = memberDao.getMember(1);
        updatedMember.setFirstName("Updated FirstName");
        updatedMember.setLastName("Updated LastName");
        updatedMember.setEmail("updated.email@example.com");
        updatedMember.setPhone("9876543210");
        updatedMember.setAddressLine1("Updated Address Street");
        updatedMember.setAddressLine2("Updated Address Street 2");
        updatedMember.setTownOrCity("Updated City");
        updatedMember.setCounty("Updated County");
        updatedMember.setPostCode("XYZ123");
        updatedMember.setDateRegistered(LocalDate.now());

        // Call the method under test
        boolean result = memberDao.updateMember(updatedMember);

        // Verify the result
        assertTrue(result, "Failed to update the member.");

        Member fetchedMember = memberDao.getMember(1);

        // Verify the details of the updated member match the fetched member
        assertEquals(updatedMember.getEmail(), fetchedMember.getEmail(), "Updated member does not match fetched member. Member ID: " + fetchedMember.getMemberID());
    }

    @Test
    void testDeleteMemberIntegration() {
        // Get the member to delete
        // Can choose any member you'd like to delete
        Member memberToDelete = memberDao.getMember(1);

        // Call the method under test
        boolean result = memberDao.deleteMember(memberToDelete.getMemberID());

        // Verify the result
        assertTrue(result, "Failed to delete the member");

        // Fetch the member again to check if it's deleted
        Member deletedMember = memberDao.getMember(memberToDelete.getMemberID());

        // Assert that the deleted member is null
        assertNull(deletedMember, "Member was not deleted");
    }








}
