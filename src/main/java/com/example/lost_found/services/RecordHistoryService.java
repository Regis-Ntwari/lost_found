package com.example.lost_found.services;

import com.example.lost_found.domain.RecordHistory;
import com.example.lost_found.repository.RecordHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordHistoryService {
    @Autowired
    RecordHistoryRepository recordHistoryRepository;

    public void saveRecordHistory(RecordHistory recordHistory){
        recordHistoryRepository.save(recordHistory);
    }
}
