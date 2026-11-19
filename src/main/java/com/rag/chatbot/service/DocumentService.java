package com.rag.chatbot.service;

import com.rag.chatbot.dto.DocumentUploadResponse;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@Service
public class DocumentService {


    private PdfProcessingService pdfProcessingService;
    private  ChunkingService chunkingService;
    private EmbeddingService embeddingService;

    // Constructor injection
    public DocumentService(PdfProcessingService pdfProcessingService, ChunkingService chunkingService,EmbeddingService embeddingService) {
        this.pdfProcessingService = pdfProcessingService;
        this.chunkingService = chunkingService;
        this.embeddingService = embeddingService;
    }

    public DocumentUploadResponse uploadDocument(MultipartFile file) {
        if(file==null || file.isEmpty()){
            throw  new IllegalArgumentException("Please upload the a PDF file");
        }
        String fileName = file.getOriginalFilename();

        if(fileName==null || !fileName.toLowerCase().endsWith(".pdf")){
            throw  new IllegalArgumentException("Only PDF files are allowed");
        }

        try{
            // extract the text from the pdf
            List<Document> documents = pdfProcessingService.extractText(file);
            // print pdf information
            int totalCharacters = documents.stream().mapToInt(doc->doc.getText().length()).sum();
            System.out.println("PDF: " + fileName);
            System.out.println("Pages extracted: " + documents.size());
            System.out.println("Total characters: " + totalCharacters);


            // split extracted pdf text into smaller chunks
            List<Document> chunks = chunkingService.splitIntoChunks(documents);
            // print chunks information
            for (int i = 0; i < Math.min(chunks.size(), 3); i++) {
                System.out.println("Chunk " + (i + 1) + ":");
                System.out.println(chunks.get(i).getText());
            }
            System.out.println("Pages extracted: " + documents.size());
            System.out.println("Total chunks generated: " + chunks.size());

            // Generate embeddings for all chunks
            List<float[]> embeddings = embeddingService.generateEmbeddings(chunks);
           // Print embedding information
            System.out.println("Total embeddings: " + embeddings.size());
            if (!embeddings.isEmpty()) {
                System.out.println("Embedding dimensions: " + embeddings.get(0).length);
                System.out.println("First embedding sample: " + java.util.Arrays.toString(java.util.Arrays.copyOf(embeddings.get(0), 5)));
            }

        }
        catch (IOException e) {
            throw new RuntimeException(
                    "Error while processing PDF", e
            );
        }

        return new DocumentUploadResponse(fileName,"PDF received successfully. Processing is not implemented yet.",true);
    }
}
