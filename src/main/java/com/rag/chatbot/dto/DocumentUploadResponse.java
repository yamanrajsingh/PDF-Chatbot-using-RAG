package com.rag.chatbot.dto;

public record DocumentUploadResponse (String fileName,
        String message,
        boolean success)

{

}
