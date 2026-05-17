package com.gpt5.gpt5.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    private final ChatClient chatClient;

    public OllamaService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String response(String userPrompt) {
        try {

            String systemPrompt = """
                    You are EduAI Assistant, a helpful educational chatbot.
                    Focus on Computer Science topics like DSA, DBMS, OS, Java, Spring Boot, and career guidance.
                    Give short, accurate, and clear answers in 3-5 lines unless detailed explanation is asked.
                    """;

            return chatClient.prompt()
                    .system(systemPrompt)
                    .user(userPrompt)
                    .call()
                    .content();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to generate response.";
        }
    }
}