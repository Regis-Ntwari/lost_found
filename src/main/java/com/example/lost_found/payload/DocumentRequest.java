package com.example.lost_found.payload;

public class DocumentRequest {
    private String bearerName;
    private String refNumber;
    private String documentType;
    private String location;

    public DocumentRequest() {
        super();
    }
    public DocumentRequest(String bearerName, String refNumber, String documentType, String location) {
        super();
        this.bearerName = bearerName;
        this.refNumber = refNumber;
        this.documentType = documentType;
        this.location = location;
    }

    public void setBearerName(String bearerName) {
        this.bearerName = bearerName;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
    public String getBearerName() {
        return bearerName;
    }
    public String getDocumentType() {
        return documentType;
    }
    public String getLocation() {
        return location;
    }
    public String getRefNumber() {
        return refNumber;
    }
}
