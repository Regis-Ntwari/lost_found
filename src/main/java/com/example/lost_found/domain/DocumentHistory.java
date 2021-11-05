package com.example.lost_found.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Document document;
    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;
    private LocalDate dateOfAction;
    private String location;

    public DocumentHistory(Document document, DocumentStatus documentStatus, LocalDate dateOfAction, String location) {
        this.document = document;
        this.documentStatus = documentStatus;
        this.dateOfAction = dateOfAction;
        this.location = location;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDateOfAction(LocalDate dateOfAction) {
        this.dateOfAction = dateOfAction;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDate getDateOfAction() {
        return dateOfAction;
    }
    public Document getDocument() {
        return document;
    }
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }
    public String getLocation() {
        return location;
    }
    public int getId() {
        return id;
    }
}
