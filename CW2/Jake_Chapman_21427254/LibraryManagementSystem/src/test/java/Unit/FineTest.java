package Unit;

import daos.BookDAO;
import daos.FineDAO;
import models.Book;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import models.Fine;
import models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class FineTest {

   FineDAO fineDao;

    @BeforeEach
    void setUp() {
        fineDao = new FineDAO();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/AddFine.csv")
    void testAddFine(String memberId, String amount, String reason, String dateIssued, String datePaid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int parsedMemberId = Integer.parseInt(memberId);
        double parsedAmount = Double.parseDouble(amount);
        LocalDate issuedDate = LocalDate.parse(dateIssued, formatter);
        LocalDate paidDate = (datePaid != null && !datePaid.isEmpty()) ? LocalDate.parse(datePaid, formatter) : null;


        Fine fine = new Fine(parsedMemberId, parsedAmount, reason, issuedDate, paidDate);

        boolean wasAdded = fineDao.addFine(fine);

        assertTrue(wasAdded, "Failed to add fine.");
    }

    @Test
    public void testAddFineWithMemberId() {

        int MemberId = 1;
        double parsedAmount = 20.0;
        String reason = "late return";
        String dateIssued = "20/09/2023";
        String datePaid = "20/12/2023";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate issuedDate = LocalDate.parse(dateIssued, formatter);
        LocalDate paidDate = (datePaid != null && !datePaid.isEmpty()) ? LocalDate.parse(datePaid, formatter) : null;

        Fine fine = new Fine(MemberId, parsedAmount, reason, issuedDate, paidDate);

        // Call the method under test
        boolean wasAdded = fineDao.addFine(fine);

        // Verify the result
        assertTrue(wasAdded, "Failed to add fine.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteFine.csv")
    void testDeleteFine(int fineID) {

        boolean wasDeleted = fineDao.deleteFine(fineID);

        assertTrue( wasDeleted, "Failed to delete fine with FineID: " + fineID);
    }

    @Test
    void testFetchAllFines() {
        // Call the method under test
        List<Fine> result = fineDao.fetchAllFines();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Expected at least one fine in the result");
    }





    }



