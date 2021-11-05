package com.example.lost_found.services;

import com.example.lost_found.domain.DocumentHistory;
import com.example.lost_found.repository.DocumentHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentHistoryService {
    @Autowired
    private DocumentHistoryRepository documentHistoryRepository;

    public void saveDocumentHistory(DocumentHistory documentHistory){
        documentHistoryRepository.save(documentHistory);
    }
}
