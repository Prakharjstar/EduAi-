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
                    You are EduAI Assistant, a helpful educational chatbot for students.
                    Focus on DSA, DBMS, OS, Java, exams, career guidance, and academic support.
                    Provide short, accurate, student-friendly answers.
                    For institution-specific topics like marks, passing criteria, or admissions, mention that rules may vary.
                    Avoid overexplaining unless asked.
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