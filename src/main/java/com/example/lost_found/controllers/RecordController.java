package com.example.lost_found.controllers;

import java.time.LocalDate;

import com.example.lost_found.domain.Record;
import com.example.lost_found.domain.RecordStatus;
import com.example.lost_found.payload.RecordRequest;
import com.example.lost_found.services.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping()
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> saveRecord(@RequestBody RecordRequest request){
        Record record = new Record(
            request.getCardNumber(), 
            request.getName(), 
            request.getPhone(), 
            request.getEmail(), 
            RecordStatus.PENDING, 
            LocalDate.now());

        return ResponseEntity.ok(recordService.saveRecord(record));
    }
    @GetMapping("/complete")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> returnAllCompletedRecords(){
        return ResponseEntity.ok(recordService.findAllCompletedRecords());
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> returnAllPendingRecords(){
        return ResponseEntity.ok(recordService.findAllPendingRecords());
    }

    @GetMapping("/one")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> returnOneRecord(@RequestParam String refNumber){
        return ResponseEntity.ok(recordService.findRecordByRefNumber(refNumber));
    }
}
