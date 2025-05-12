package com.javalab.java.ai.langchain4j;

import com.javalab.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.javalab.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testSystemPrompt() {
        String chat = separateChatAssistant.chat(5,"今天几号？");
        System.out.println(chat);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Test
    public void testUserMessage(){
        String chat = memoryChatAssistant.chat("今天几号？");
        System.out.println(chat);

        String chat1 = memoryChatAssistant.chat("你知道我是谁吗？");
        System.out.println(chat1);

        String chat2 = memoryChatAssistant.chat("你是谁？");
        System.out.println(chat2);
    }

    @Test
    public void testV(){
        String chat = separateChatAssistant.chat(10, "我是关羽" );
        System.out.println(chat);
        String chat2 = separateChatAssistant.chat2(10, "你是谁？" );
        System.out.println(chat2);
    }

    @Test
    public void testuserInfo(){
        String username = "关羽";
        int age = 30;
        String chat3 = separateChatAssistant.chat3(20, "我关羽", username, age);
        System.out.println(chat3);
    }
}
