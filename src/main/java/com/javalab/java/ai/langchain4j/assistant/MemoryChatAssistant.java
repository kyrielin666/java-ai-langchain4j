package com.javalab.java.ai.langchain4j.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory")
public interface MemoryChatAssistant {
    @UserMessage("你是我的好朋友，请用粤语回答我的问题，并且添加一些符号。{{message}} ")
    String chat(@V("message") String message);
}
