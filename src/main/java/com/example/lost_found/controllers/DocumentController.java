package com.example.lost_found.controllers;

import java.time.LocalDate;
import java.util.List;

import com.example.lost_found.domain.Document;
import com.example.lost_found.domain.DocumentHistory;
import com.example.lost_found.domain.DocumentStatus;
import com.example.lost_found.domain.DocumentType;
import com.example.lost_found.payload.DocumentRequest;
import com.example.lost_found.services.DocumentHistoryService;
import com.example.lost_found.services.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/document")
@PreAuthorize("hasAuthority('STAFF')")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentHistoryService documentHistoryService;

    @PostMapping("/")
    public ResponseEntity<?> saveDocument(@RequestBody DocumentRequest request){
        Document document = documentService.findByRefNumber(request.getRefNumber());
        if(document == null){
            document = new Document(
                request.getBearerName(), 
                request.getRefNumber(), 
                DocumentType.valueOf(request.getDocumentType()), 
                DocumentStatus.FOUND, 
                request.getLocation());
        }else{
            document.setDocumentStatus(DocumentStatus.FOUND);
            document.setLocation(request.getLocation());
        }
        documentService.saveDocument(document);
        documentHistoryService.saveDocumentHistory(new DocumentHistory(document, document.getDocumentStatus(), LocalDate.now(), document.getLocation()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/issue")
    public ResponseEntity<?> issueDocument(@RequestParam int id){
        Document document = documentService.findById(id);
        if(document == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(document.getDocumentStatus().name().equals("ISSUED")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        document.setDocumentStatus(DocumentStatus.ISSUED);
        documentHistoryService.saveDocumentHistory(new DocumentHistory(document, document.getDocumentStatus(), LocalDate.now(), document.getLocation()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findDocumentByRefNumber(@RequestParam String refNumber){
        Document document = documentService.findByRefNumber(refNumber);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/name")
    public ResponseEntity<?> findDocumentsByBearerName(@RequestParam String bearerName){
        List<Document> documents = documentService.findByBearerName(bearerName);
        return ResponseEntity.ok(documents);
    }
}
