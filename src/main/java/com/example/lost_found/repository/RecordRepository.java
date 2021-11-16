package com.example.lost_found.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.lost_found.domain.Record;
import com.example.lost_found.domain.RecordStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer>{
    List<Record> findByCardNumber(String cardNumber);
    List<Record> findByRecordStatus(RecordStatus recordStatus);
    List<Record> findByDateOfSubmission(LocalDate date);

    @Query(value = "SELECT * FROM Record r WHERE r.card_number = ?1 AND r.record_status = 'PENDING'", nativeQuery = true)
    Record findPendingRecordByRefNumber(String refNumber);
}
