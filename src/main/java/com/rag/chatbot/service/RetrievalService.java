package com.rag.chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrievalService {
    @Autowired
    private VectorStore vectorStore;

    public List<Document> retrievalRelevantChunks(String query)
    {
        if(query == null || query.isEmpty())
        {
            throw  new IllegalArgumentException("Search query cannot be empty");
        }

        SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(5)
                .similarityThreshold(0.5)
                .build();

        return  vectorStore.similaritySearch(searchRequest);

    }

}
