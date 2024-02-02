package daos;

import models.Fine;
import database.DatabaseHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for fine-related database operations.
 */
public class FineDAO {

    private final DatabaseHandler dbHandler;
    private Connection conn;

    /**
     * Constructor initializes DatabaseHandler.
     */
    public FineDAO() {
        dbHandler = new DatabaseHandler();
    }

    /**
     * Adds a new fine to the database.
     * @param fine Fine object to be added.
     * @return true if successfully added, false otherwise.
     */
    public boolean addFine(Fine fine) {
        String insertFineQuery = "INSERT INTO Fine (MemberID, Amount, Reason, DateIssued, DatePaid) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertFineQuery);
            pstmt.setInt(1, fine.getMemberId());
            pstmt.setDouble(2, fine.getAmount());
            pstmt.setString(3, fine.getReason());
            pstmt.setDate(4, java.sql.Date.valueOf(fine.getDateIssued()));
            pstmt.setDate(5, fine.getDatePaid() != null ? java.sql.Date.valueOf(fine.getDatePaid()) : null);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Deletes a fine from the database.
     * @param fineID ID of the fine to be deleted.
     * @return true if successfully deleted, false otherwise.
     */
    public boolean deleteFine(int fineID) {
        String deleteQuery = "DELETE FROM Fine WHERE FineID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, fineID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fetches all fines from the database.
     * @return List of Fine objects.
     */
    public List<Fine> fetchAllFines() {
        List<Fine> fines = new ArrayList<>();
        String fetchQuery = "SELECT * FROM Fine";
        try {
            PreparedStatement pstmt = conn.prepareStatement(fetchQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Fine fine = new Fine();
                fine.setFineId(rs.getInt("FineID"));
                fine.setMemberId(rs.getInt("MemberID"));
                fine.setAmount(rs.getDouble("Amount"));
                fine.setReason(rs.getString("Reason"));
                fine.setDateIssued(rs.getDate("DateIssued").toLocalDate());
                fine.setDatePaid(rs.getDate("DatePaid") != null ? rs.getDate("DatePaid").toLocalDate() : null);
                fines.add(fine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fines;
    }
}