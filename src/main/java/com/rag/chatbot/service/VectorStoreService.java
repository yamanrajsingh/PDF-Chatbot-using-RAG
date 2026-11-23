package com.rag.chatbot.service;


import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorStoreService {

    @Autowired
    private VectorStore vectorStore;

    public void savedChunks(List<Document> chunks){
        if(chunks== null || chunks.isEmpty()){
            throw  new IllegalArgumentException("chunks is null or empty");
        }
        else {
            vectorStore.add(chunks);
            System.out.println("Successfully stored " + chunks.size() + " chunks");
        }
    }


}
