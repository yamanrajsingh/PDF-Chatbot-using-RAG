package com.rag.chatbot.controller;

import com.rag.chatbot.dto.ChatAnswerResponse;
import com.rag.chatbot.dto.ChatRequest;
import com.rag.chatbot.dto.ChatResponse;
import com.rag.chatbot.service.ChatService;
import com.rag.chatbot.service.RetrievalService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private  RetrievalService retrievalService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/retrieve")
    public ResponseEntity<ChatResponse> retrieve(@RequestBody ChatRequest chatRequest) {
        List<Document> documents = retrievalService.retrievalRelevantChunks(chatRequest.question());

        List<String> chunks = documents.stream().map(Document::getText).toList();
        ChatResponse chatResponse = new ChatResponse(chatRequest.question(), chunks);
        return  ResponseEntity.ok(chatResponse);
    }

    @PostMapping("/ask")
    public ResponseEntity<ChatAnswerResponse> askQuestion(@RequestBody ChatRequest chatRequest) {
        String ans = chatService.generateAnswer(chatRequest.question());
        ChatAnswerResponse chatAnswerResponse = new ChatAnswerResponse(chatRequest.question(), ans);
        return  ResponseEntity.ok(chatAnswerResponse);
    }


}
