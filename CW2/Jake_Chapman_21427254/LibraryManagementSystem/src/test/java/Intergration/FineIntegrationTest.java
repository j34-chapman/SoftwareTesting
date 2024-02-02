package Intergration;

import models.Fine;
import daos.FineDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class FineIntegrationTest {

    private FineDAO fineDao;

    @BeforeEach
    void setUp() {
        fineDao = new FineDAO();
    }


    @Test
    void testAddFineIntegration() {
        // Create Fine object for testing
        Fine fine = new Fine(1, 20.0, "Late return", LocalDate.of(2023, 9, 20), LocalDate.of(2023, 12, 20));

        // Call the method under test
        boolean wasAdded = fineDao.addFine(fine);

        // Verify the result
        assertTrue(wasAdded, "Failed to add fine to the database");
    }

    @Test
    void testDeleteFineIntegration() {
        // Assume there is an existing fine in the database with FineID 1 for testing deletion
        int fineIDToDelete = 1;

        // Call the method under test
        boolean wasDeleted = fineDao.deleteFine(fineIDToDelete);

        // Verify the result
        assertTrue(wasDeleted, "Failed to delete fine from the database");
    }

    @Test
    void testFetchAllFinesIntegration() {
        // Call the method under test
        List<Fine> result = fineDao.fetchAllFines();

        // Verify the result
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Expected at least one fine in the result");
    }

}