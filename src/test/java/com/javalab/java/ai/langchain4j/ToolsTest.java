package com.javalab.java.ai.langchain4j;

import com.javalab.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testCalculatorTools(){
        String chat = separateChatAssistant.chat(98, "1+9等于几吗？56756756的平方根是多少？" );
        System.out.println(chat);
    }
}
