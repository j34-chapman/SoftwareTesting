package models;

import java.time.LocalDate;

/**
 * Represents a member in the library management system.
 */
public class Member {

    // Attributes based on the database schema for the Members table
    private int memberID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String townOrCity;
    private String county;
    private String postCode;
    private LocalDate dateRegistered;

    /**
     * Constructor to initialize a member with all attributes.
     *
     * @param memberID       Unique ID for the member.
     * @param firstName      First name of the member.
     * @param lastName       Last name of the member.
     * @param email          Email address of the member.
     * @param phone          Phone number of the member.
     * @param addressLine1   First line of the member's address.
     * @param addressLine2   Second line of the member's address (if applicable).
     * @param townOrCity     Town or City of the member.
     * @param county         County of the member.
     * @param postCode       Post code of the member.
     * @param dateRegistered Date when the member was registered.
     */
    public Member(int memberID, String firstName, String lastName, String email, String phone,
                  String addressLine1, String addressLine2, String townOrCity, String county,
                  String postCode, LocalDate dateRegistered) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.townOrCity = townOrCity;
        this.county = county;
        this.postCode = postCode;
        this.dateRegistered = dateRegistered;
    }

    public Member(String firstName, String lastName, String email, String phone, String addressLine1, String addressLine2, String townOrCity, String county, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.townOrCity = townOrCity;
        this.county = county;
        this.postCode = postCode;
    }

    public Member() {

    }

    public Member(String firstName, String lastName, String email, String phone, String s, String s1, String townOrCity, String county, String postCode, LocalDate dateRegistered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.addressLine1 = s;
        this.addressLine2 = s1;
        this.townOrCity = townOrCity;
        this.county = county;
        this.postCode = postCode;
        this.dateRegistered = dateRegistered;
    }


    // Getters and Setters

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

}
