package com.rag.chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class ChunkingService {

    private TokenTextSplitter tokenTextSplitter;

    public  ChunkingService() {
        this.tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(800)
                .withMinChunkSizeChars(350)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(10000)
                .withKeepSeparator(true)
                .build();
    }
    public List<Document> splitIntoChunks(List<Document> documents) {
        return tokenTextSplitter.apply(documents);
    }
}
