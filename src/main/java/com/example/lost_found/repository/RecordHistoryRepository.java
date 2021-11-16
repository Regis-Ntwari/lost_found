package com.example.lost_found.repository;

import com.example.lost_found.domain.RecordHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordHistoryRepository extends JpaRepository<RecordHistory, Integer>{
    
}
