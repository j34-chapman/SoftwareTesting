package models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents a fine issued to a member for a late book return.
 */
public class Fine {
    private int fineId;
    private int memberId;
    private double amount;
    private String reason;
    private LocalDate dateIssued;
    private LocalDate datePaid;

    /**
     * Constructor for Fine.
     * @param fineId Fine ID.
     * @param memberId Member ID.
     * @param amount Fine amount.
     * @param reason Reason for fine.
     * @param dateIssued Date the fine was issued.
     * @param datePaid Date the fine was paid.
     */
    public Fine(int fineId, int memberId, double amount, String reason, LocalDate dateIssued, LocalDate datePaid) {
        this.fineId = fineId;
        this.memberId = memberId;
        this.amount = amount;
        this.reason = reason;
        this.dateIssued = dateIssued;
        this.datePaid = datePaid;
    }

    public Fine(int memberId, double amount, String reason, LocalDate dateIssued, LocalDate datePaid) {
        this.memberId = memberId;
        this.amount = amount;
        this.reason = reason;
        this.dateIssued = dateIssued;
        this.datePaid = datePaid;
    }

    public Fine() {

    }

    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        long daysLate = DAYS.between(dueDate, returnDate);
        double fineAmount = 0;

        if (daysLate > 0 && daysLate < 7) {
            fineAmount = 1; // £1 for 1 to 7 days late
        } else if (daysLate > 7 && daysLate < 14) {
            fineAmount = 5; // £5 for 8 to 14 days late
        } else if (daysLate > 14) {
            fineAmount = 5 + (daysLate - 14); // £5 plus £1 for each day after 14 days
        }

        return fineAmount;
    }


    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }
}
