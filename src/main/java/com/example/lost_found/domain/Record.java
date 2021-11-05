package com.example.lost_found.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;
    private String nameOfBearer;
    private String telephone;
    private String email;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public Record(String cardNumber, String nameOfbearer, String telephone,String email, RecordStatus recordStatus) {
        this.cardNumber = cardNumber;
        this.nameOfBearer = nameOfbearer;
        this.telephone = telephone;
        this.email = email;
        this.recordStatus = recordStatus;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNameOfBearer(String nameOfBearer) {
        this.nameOfBearer = nameOfBearer;
    }
    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getNameOfBearer() {
        return nameOfBearer;
    }
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }
    public String getTelephone() {
        return telephone;
    }
}
