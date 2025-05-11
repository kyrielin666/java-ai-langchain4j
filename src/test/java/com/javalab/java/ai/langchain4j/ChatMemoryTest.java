package com.javalab.java.ai.langchain4j;

import com.javalab.java.ai.langchain4j.assistant.Assistant;
import com.javalab.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.javalab.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class  ChatMemoryTest {
    @Autowired
    private Assistant assistant;

    @Test
    public void testChat() {
        String chat = assistant.chat("你是谁？");
        System.out.println(chat);

        String chat1 = assistant.chat("我是谁" );
        System.out.println(chat1);
    }

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory2() {
        //round1
        UserMessage userMessage1 = UserMessage.userMessage("我是环环");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage = chatResponse1.aiMessage();
        System.out.println(aiMessage.text());

        //round2
        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗？" );
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChatMemory3(){
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();

        String chat = assistant.chat("我是环环" );
        System.out.println(chat);
        String chat1 = assistant.chat("你知道我是谁吗？" );
        System.out.println(chat1);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory4(){
        String chat = separateChatAssistant.chat(1,"我是环环" );
        System.out.println(chat);
        String chat1 = separateChatAssistant.chat(1,"我是谁？" );
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat(3, "我是谁？" );
        System.out.println(chat2 );
    }
}
