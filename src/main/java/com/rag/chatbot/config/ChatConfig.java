
package com.rag.chatbot.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChatConfig {

    @Bean
    @Primary
    public ChatModel primaryChatModel(
            @Qualifier("googleGenAiChatModel")
            ChatModel geminiChatModel
    ) {
        return geminiChatModel;
    }
}
