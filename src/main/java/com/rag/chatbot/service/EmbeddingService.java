package com.rag.chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService {
    @Autowired
    private EmbeddingModel embeddingModel;

    public List<float[]> generateEmbeddings(List<Document> chunks)
    {
        List<float[]> embeddings =new ArrayList<>();
        for (Document chunk : chunks) {
            float[] vector = this.embeddingModel.embed(chunk.getText());
            embeddings.add(vector);
        }
        return  embeddings;
    }
}
