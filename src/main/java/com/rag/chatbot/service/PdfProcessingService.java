package com.rag.chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class PdfProcessingService {

    public List<Document> extractText(MultipartFile file) throws IOException {

        // Convert uploaded PDF into Spring Resource
        ByteArrayResource resource = new ByteArrayResource(
                file.getBytes()
        ) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // Read PDF using Spring AI
        PagePdfDocumentReader pdfReader =
                new PagePdfDocumentReader(resource);

        // Extract text and return documents
        return pdfReader.read();
    }
}
