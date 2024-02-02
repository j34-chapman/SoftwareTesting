package models;

import java.time.LocalDate;

/**
 * Represents a loan in the library management system.
 * A loan is made when a member borrows a book.
 */
public class Loan {

    /** Unique identifier for the loan. */
    private int loanId;

    /** The ID of the book that was borrowed. */
    private int bookId;

    /** The ID of the member who borrowed the book. */
    private int memberId;

    /** The date the book was borrowed. */
    private LocalDate loanDate;

    /** The date the book is due to be returned */
    private LocalDate dueDate;

    /** The date the book was returned, null if not yet returned. */
    private LocalDate returnDate;

    private Fine fine;

    /**
     * Default constructor.
     */
    public Loan() {
        // Empty implementation
    }

    /**
     * Constructs a new Loan instance with the specified member ID, book ID, borrow date, and return date.
     *
     * @param memberId The ID of the member who is borrowing the book.
     * @param bookId The ID of the book being borrowed.
     * @param loanDate The date when the book was borrowed.
     * @param returnDate The date when the book is expected to be returned.
     */
    public Loan(int memberId, int bookId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Loan(int memberId, int bookId, LocalDate loanDate, LocalDate dueDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    // Getter and Setter methods

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the Fine associated with this loan.
     * @return Fine object.
     */
    public Fine getFine() {
        return fine;
    }

    /**
     * Sets the Fine associated with this loan.
     * @param fine Fine object.
     */
    public void setFine(Fine fine) {
        this.fine = fine;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
