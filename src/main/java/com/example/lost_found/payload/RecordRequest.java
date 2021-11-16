package com.example.lost_found.payload;

public class RecordRequest {
    private String name;
    private String cardNumber;
    private String phone;
    private String email;

    public RecordRequest() {
        super();
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
}
