package com.rag.chatbot.dto;

public record DocumentUploadResponse (
        String fileName,
        String documentId,
        String message,
        boolean success)

{

}
