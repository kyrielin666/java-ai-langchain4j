package com.javalab.java.ai.langchain4j.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ChatLanguageModelConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("qwen-vl-max")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }
}
