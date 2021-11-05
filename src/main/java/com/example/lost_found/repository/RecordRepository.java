package com.example.lost_found.repository;

import com.example.lost_found.domain.Record;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer>{
    
}
