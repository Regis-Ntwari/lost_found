package com.example.lost_found.services;

import java.util.List;

import com.example.lost_found.domain.Document;
import com.example.lost_found.domain.Record;
import com.example.lost_found.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RecordService recordService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public Document saveDocument(Document document) {
        Record record = recordService.findPendingRecordByRefNumber(document.getRefNumber());
        Document savedDocument = documentRepository.save(document);
        if (record != null) {
            if (record.getEmail().equals("")) {

                emailServiceImpl.sendEmailMessage(record.getEmail(), "ID UPDATE",
                        "Your ID has been submitted. Please report to " + savedDocument.getLocation()
                                + " to get your ID");
            }
        }
        return savedDocument;
    }

    public Document findById(int id) {
        return documentRepository.getById(id);
    }

    public Document findByRefNumber(String refNumber) {
        return documentRepository.findByRefNumber(refNumber);
    }

    public List<Document> findByBearerName(String bearerName) {
        return documentRepository.findByBearerName(bearerName);
    }
}
