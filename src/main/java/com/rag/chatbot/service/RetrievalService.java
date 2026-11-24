package com.rag.chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RetrievalService {
    @Autowired
    private VectorStore vectorStore;

    public List<Document> retrievalRelevantChunks(String query, String documentId)
    {
        if(query == null || query.isEmpty())
        {
            throw  new IllegalArgumentException("Search query cannot be empty");
        }
        if (documentId == null || documentId.isBlank()) {
            throw new IllegalArgumentException("Document ID cannot be empty");
        }

        String validatedDocumentId;
        try{
            validatedDocumentId = UUID.fromString(documentId).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid document ID");
        }

        SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(5)
                .similarityThreshold(0.5)
                .filterExpression("documentId == '" + validatedDocumentId + "'")
                .build();

        return  vectorStore.similaritySearch(searchRequest);

    }

}
