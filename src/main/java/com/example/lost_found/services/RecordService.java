package com.example.lost_found.services;

import java.util.Optional;

import com.example.lost_found.domain.Record;
import com.example.lost_found.domain.RecordStatus;
import com.example.lost_found.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public Record saveRecord(Record record){
        record.setRecordStatus(RecordStatus.PENDING);
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
}
