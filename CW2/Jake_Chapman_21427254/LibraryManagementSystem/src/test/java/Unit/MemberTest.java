package Unit;

import daos.BookDAO;
import daos.LoanDAO;
import daos.MemberDAO;
import models.Book;
import models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private MemberDAO memberDao;

    @BeforeEach
    void setUp() {
        // Create an instance of LoanDAO
        memberDao = new MemberDAO();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/AddMember.csv")
    public void testAddMember(String FirstName, String LastName, String Email, String Phone, String AddressLine1,
                              String AddressLine2, String TownOrCity, String County, String PostCode,
                              String DateRegistered) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateParsed = LocalDate.parse(DateRegistered, formatter);



        Member member = new Member(FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, TownOrCity, County, PostCode, dateParsed);

        boolean wasAdded = memberDao.addMember(member);

        assertTrue(wasAdded, "Failed to add member.");

    }

    @Test
    public void testAddMemberWithId() {

        int MemberId = 1;
        String firstName = "John";
        String lastName = "Smith";
        String email = "john.Smith@example.com";
        String phone = "1234567890";
        String addressLine1 = "Ronnie Rd";
        String addressLine2 = "Pickering";
        String townOrCity = "City";
        String county = "County";
        String postCode = "M1X XXX";
        String dateRegistered = "20/12/2023";  // Replace with the actual date

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateParsed = LocalDate.parse(dateRegistered, formatter);

        Member member = new Member(MemberId, firstName, lastName, email, phone, addressLine1, addressLine2, townOrCity, county, postCode, dateParsed);

        // Call the method under test
        boolean wasAdded = memberDao.addMember(member);

        // Verify the result
        assertTrue(wasAdded, "Failed to add member.");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/GetMember.csv")
    void testGetMember(int memberID) {
        Member retrievedMember = memberDao.getMember(memberID);


        assertNull(retrievedMember, "Book with ID " + memberID + " was not retrieved");
        //assertEquals(bookID, retrievedBook.getBookID(), "Retrieved book has incorrect ID");

    }


    @Test
    void testGetAllMembers() throws SQLException {
       ;

        // Call the method under test
        List<Member> result = memberDao.getAllMembers();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Expected at least one member in the result");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UpdateMember.csv")
    public void testUpdateMember(String memberID, String FirstName, String LastName, String Email, String Phone, String AddressLine1,
                                String AddressLine2, String TownOrCity, String County, String PostCode, String DateRegistered) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(DateRegistered, formatter);

        // Parse bookID to int
        int parsedMemberID = Integer.parseInt(memberID);

        Member member = new Member(parsedMemberID, FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, TownOrCity, County, PostCode, parsedDate);

        boolean wasAdded = memberDao.updateMember(member);

        assertTrue(wasAdded, "Failed to updateMember.");


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteMember.csv")
    void testDeleteBook(int bookIdToDelete) {
        //Act

        // Attempt to delete the book
        boolean DeletionResult = memberDao.deleteMember(bookIdToDelete);

        //Assert
        // Check if the actual deletion works
        assertTrue(DeletionResult ,"Member deletion was unsucessful.");


    }
}