package com.javalab.java.ai.langchain4j;

import com.javalab.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory() {
        String answer = assistant.chat("我是环环");
        System.out.println(answer);

        String answer2 = assistant.chat("我是谁？");
        System.out.println(answer2);
    }

    @Test
    public void testChatMemory2() {
        UserMessage userMessage = UserMessage.userMessage("我是环环");
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println(aiMessage.text());

        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗？");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage, aiMessage, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

}
