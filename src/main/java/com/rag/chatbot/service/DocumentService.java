package com.rag.chatbot.service;

import com.rag.chatbot.dto.DocumentUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {
    public DocumentUploadResponse uploadDocument(MultipartFile file) {
        if(file==null || file.isEmpty()){
            throw  new IllegalArgumentException("Please upload the a PDF file");
        }
        String fileName = file.getOriginalFilename();

        if(fileName==null || !fileName.toLowerCase().endsWith(".pdf")){
            throw  new IllegalArgumentException("Only PDF files are allowed");

        }
        return new DocumentUploadResponse(fileName,"PDF received successfully. Processing is not implemented yet.",true);
    }
}
