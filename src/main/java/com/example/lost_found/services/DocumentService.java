package com.example.lost_found.services;

import java.util.List;

import com.example.lost_found.domain.Document;
import com.example.lost_found.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document){
        return documentRepository.save(document);
    }
    public Document findById(int id){
        return documentRepository.getById(id);
    }
    public Document findByRefNumber(String refNumber){
        return documentRepository.findByRefNumber(refNumber);
    }
    public List<Document> findByBearerName(String bearerName){
        return documentRepository.findByBearerName(bearerName);
    }
}
