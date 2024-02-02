package daos;

import database.DatabaseHandler;
import models.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    private DatabaseHandler dbHandler;

    public LoanDAO() {
        dbHandler = new DatabaseHandler(); // Assumes you have a default constructor that sets up connection details
    }

    /**
     * Returns a book by updating the loan's return date.
     *
     * @param loanId The ID of the loan to be updated.
     * @return true if the update is successful, false otherwise.
     */
    public boolean returnBook(int loanId) {
        String sql = "UPDATE Loans SET returnDate = CURRENT_TIMESTAMP WHERE loanId = ?";

        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loanId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Borrows a book by inserting a new loan and updating the book's availability status.
     *
     * @param loan The loan object containing the details of the loan.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean borrowBook(Loan loan) {
        String sql = "INSERT INTO Loans (memberId, bookId, loanDate, dueDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getMemberId());
            stmt.setInt(2, loan.getBookId());
            stmt.setObject(3, loan.getLoanDate());
            stmt.setObject(4, loan.getDueDate());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting the loan failed, no rows affected.");
            }

            // Update the book's availability status to 'On Loan'
            BookDAO bookDAO = new BookDAO();
            if (!bookDAO.updateBookAvailabilityStatus(loan.getBookId(), "On Loan")) {
                throw new SQLException("Updating the book's availability status failed.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all loans from the database.
     *
     * @return A list of all loans.
     */
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM Loans";

        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int loanId = rs.getInt("loanId");
                int memberId = rs.getInt("memberId");
                int bookId = rs.getInt("bookId");
                LocalDate loanDate = rs.getObject("loanDate", LocalDate.class);
                LocalDate dueDate = rs.getObject("dueDate", LocalDate.class);

                try {
                    if (rs.getObject("returnDate", LocalDate.class) != null) {
                        LocalDate returnDate = rs.getObject("returnDate", LocalDate.class);  // This might be null in the database
                        Loan loan = new Loan(memberId, bookId, loanDate, dueDate, returnDate);
                    }
                } catch (NullPointerException e) {

                }

                Loan loan = new Loan(memberId, bookId, loanDate, dueDate);
                loan.setLoanId(loanId);
                loans.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

}
