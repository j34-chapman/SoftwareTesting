package daos;

import database.DatabaseHandler;
import models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MemberDAO class provides CRUD operations for library members in the SQLite database.
 */
public class MemberDAO {

    /** Database connection handler. */
    private final DatabaseHandler dbHandler;

    /**
     * Default constructor that initializes the database handler.
     */
    public MemberDAO() {
        dbHandler = new DatabaseHandler();
    }
    /**
     * Inserts a new member into the database.
     *
     * @param member The member to be added.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean addMember(Member member) {
        String query = "INSERT INTO Members (FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, TownOrCity, County, PostCode, DateRegistered) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2, member.getLastName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddressLine1());
            pstmt.setString(6, member.getAddressLine2());
            pstmt.setString(7, member.getTownOrCity());
            pstmt.setString(8, member.getCounty());
            pstmt.setString(9, member.getPostCode());
            java.sql.Date dateRegistered = java.sql.Date.valueOf(member.getDateRegistered());
            pstmt.setDate(10, dateRegistered);


            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a member from the database by their ID.
     *
     * @param memberID The ID of the member to be retrieved.
     * @return A Member object or null if not found.
     */
    public Member getMember(int memberID) {
        String query = "SELECT * FROM Members WHERE MemberID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, memberID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member(
                        rs.getInt("MemberID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("AddressLine1"),
                        rs.getString("AddressLine2"),
                        rs.getString("TownOrCity"),
                        rs.getString("County"),
                        rs.getString("PostCode"),
                        rs.getObject("DateRegistered", LocalDate.class)
                );
                return member;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching member: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all members from the database.
     *
     * @return A list of all members.
     */
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Members";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Assuming the "DateRegistered" column is of type DATE or TIMESTAMP in the database
                java.sql.Date sqlDate = rs.getDate("DateRegistered");
                LocalDate dateRegistered = sqlDate.toLocalDate();

                Member member = new Member(
                        rs.getInt("MemberID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("AddressLine1"),
                        rs.getString("AddressLine2"),
                        rs.getString("TownOrCity"),
                        rs.getString("County"),
                        rs.getString("PostCode"),
                        dateRegistered  // Use the converted LocalDate
                );

                members.add(member);
            }
            return members;

        } catch (SQLException e) {
            System.err.println("Error fetching all members: " + e.getMessage());
        }
        return members;
    }

    /**
     * Updates a member's details in the database.
     *
     *
     * @param member The member with updated details.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean updateMember(Member member) {
        String query = "UPDATE Members SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, AddressLine1 = ?, AddressLine2 = ?, TownOrCity = ?, County = ?, PostCode = ?, DateRegistered = ? WHERE MemberID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2, member.getLastName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddressLine1());
            pstmt.setString(6, member.getAddressLine2());
            pstmt.setString(7, member.getTownOrCity());
            pstmt.setString(8, member.getCounty());
            pstmt.setString(9, member.getPostCode());
            pstmt.setObject(10, member.getDateRegistered());
            pstmt.setInt(11, member.getMemberID());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a member from the database.
     *
     * @param memberID The ID of the member to be deleted.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean deleteMember(int memberID) {
        String query = "DELETE FROM Members WHERE MemberID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, memberID);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
            return false;
        }
    }
}
