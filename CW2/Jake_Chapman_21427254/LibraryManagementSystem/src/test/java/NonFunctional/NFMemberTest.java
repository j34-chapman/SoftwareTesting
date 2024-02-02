package NonFunctional;

import daos.MemberDAO;
import models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NFMemberTest extends ApplicationTest {

    MemberDAO memberDAO;

    @BeforeEach
    void setUp() {
        memberDAO = new MemberDAO();
    }

    @Test
    void testResponseTimeForGetAllMembersMethod() {
        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        memberDAO.getAllMembers();

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;

        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForAddMemberMethod() {
        // Create a sample member
        Member sampleMember = new Member("Jake", "Chapman", "jake123@example.com", "+44 1234578", "Hello Rd",
                "Goobye St", "Lazy Town","Lazyville", "M123 345", LocalDate.of(1925, 4, 10));

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        memberDAO.addMember(sampleMember);

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate the elapsed time in milliseconds
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForDeleteMemberMethod() {
        // Replace 1 with the actual Member ID you want to delete
        int memberIdToDelete = 1;

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        memberDAO.deleteMember(memberIdToDelete);

        // Record the end time
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForGetMemberMethod() {
        // Replace 1 with the actual Member ID you want to retrieve
        int memberIdToRetrieve = 1;

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Call the method you want to test
        memberDAO.getMember(memberIdToRetrieve);

        // Record the end time
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
    }

    @Test
    void testResponseTimeForUpdateMemberMethod() {
        // Create a sample member object or use a builder method if available
        Member sampleMember = new Member(7,"Jake", "Chapman", "jakeds123@example.com", "+44 1234578", "Hello Rd",
                "Goobye St", "Lazy Town","Lazyville", "M123 345", LocalDate.of(1925, 4, 10));

        memberDAO.addMember(sampleMember);

        Member memberToUpdate = memberDAO.getMember(7);

        assertNotNull(memberToUpdate, "Member not found");

        memberToUpdate.setEmail("jakeds12356@example.com");
        memberToUpdate.setPhone("+44 1234578811");

        // Record the start time
        long startTime = System.currentTimeMillis();


        // Call the method you want to test
        boolean updateResult =  memberDAO.updateMember(sampleMember);
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        double elapsedTimeSeconds = elapsedTimeMillis / 1000.0;
        double responseTimeThreshold = 2.0;
        assertTrue(elapsedTimeSeconds <= responseTimeThreshold, "Response time exceeds threshold: " + elapsedTimeSeconds + " seconds");
        assertTrue(updateResult, "Member not updated");
       // memberDAO.deleteMember(17);
    }








}