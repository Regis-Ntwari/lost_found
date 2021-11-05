package com.example.lost_found.repository;

import java.util.List;

import com.example.lost_found.domain.Document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer>{
    public Document findByRefNumber(String refNumber);

    public List<Document> findByBearerName(String bearerName);
}
