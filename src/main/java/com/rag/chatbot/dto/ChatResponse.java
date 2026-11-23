package com.rag.chatbot.dto;

import java.util.List;

public record ChatResponse(String question, List<String> relevantChunks) {
}
