package com.example.lost_found.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Document implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bearerName;
    private String refNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;
    private String location;

    public Document(String bearerName, String refNumber, DocumentType documentType, DocumentStatus documentStatus, String location) {
        this.documentType = documentType;
        this.refNumber = refNumber;
        this.bearerName = bearerName;
        this.documentStatus = documentStatus;
        this.location = location;
    }

    public Document() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
    public void setBearerName(String nameOfBearer) {
        this.bearerName = nameOfBearer;
    }
    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public DocumentType getDocumentType() {
        return documentType;
    }
    public String getNameOfBearer() {
        return bearerName;
    }
    public String getRefNumber() {
        return refNumber;
    }
    public int getId() {
        return id;
    }
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }
    public String getLocation() {
        return location;
    }
}
