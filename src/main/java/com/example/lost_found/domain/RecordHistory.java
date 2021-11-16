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
public class RecordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Record record;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
    private LocalDate dateOfAction;

    public RecordHistory() {
        super();
    }
    public RecordHistory(Record record, RecordStatus recordStatus, LocalDate dateOfAction) {
        super();
        this.record = record;
        this.recordStatus = recordStatus;
        this.dateOfAction = dateOfAction;
    }
    public void setDateOfAction(LocalDate dateOfAction) {
        this.dateOfAction = dateOfAction;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setRecord(Record record) {
        this.record = record;
    }
    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
    public LocalDate getDateOfAction() {
        return dateOfAction;
    }
    public int getId() {
        return id;
    }
    public Record getRecord() {
        return record;
    }
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }
}
