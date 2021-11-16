package com.example.lost_found.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.lost_found.domain.Document;
import com.example.lost_found.domain.Record;
import com.example.lost_found.domain.RecordStatus;
import com.example.lost_found.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public Record saveRecord(Record record){
        record.setRecordStatus(RecordStatus.PENDING);
        Document document = documentService.findByRefNumber(record.getCardNumber());
        Record recordExist = recordRepository.findPendingRecordByRefNumber(record.getCardNumber());
        if(recordExist != null){
            return new Record();
        }
        if(document != null){
            emailServiceImpl.sendEmailMessage(
                record.getEmail(), 
                document.getDocumentType().name().equals("ID_CARD") ? "ID CARD UPDATE" : "DRIVER LICENSE UPDATE", 
                "Your Card has been found. Please report to " + document.getLocation() + " to get your card.");
        }
        return recordRepository.save(record);
    }
    public Optional<Record> findById(int id){
        return recordRepository.findById(id);
    }
    public Record completeRecord(int id){
        Record record = recordRepository.findById(id).get();
        record.setRecordStatus(RecordStatus.COMPLETED);
        return recordRepository.save(record);
    }
    public List<Record> findRecordByRefNumber(String refNumber){
        return recordRepository.findByCardNumber(refNumber);
    }
    public List<Record> findAllPendingRecords(){
        return recordRepository.findByRecordStatus(RecordStatus.PENDING);
    }
    public List<Record> findAllCompletedRecords(){
        return recordRepository.findByRecordStatus(RecordStatus.COMPLETED);
    }
    public List<Record> findAllTodayRecords(){
        return recordRepository.findByDateOfSubmission(LocalDate.now());
    }
    public List<Record> findByCertainDateRecords(LocalDate date){
        return recordRepository.findByDateOfSubmission(date);
    }
    public Record findPendingRecordByRefNumber(String refNumber){
        return recordRepository.findPendingRecordByRefNumber(refNumber);
    }
}
