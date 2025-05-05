package com.javalab.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;

@SpringBootTest
public class LLMTest {

    @Test
    public void testLLM() {

        OpenAiChatModel model = OpenAiChatModel.builder()
                  .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer=  model.chat("你好");
        System.out.println(answer);

    }

    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Test
    public void testLLM2() {
        String answer = openAiChatModel.chat("我是谁？");
        System.out.println(answer);
    }

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testLLM3() {
        String answer = qwenChatModel.chat("我是谁？");
        System.out.println(answer);
    }

    @Test
    public void testLLM4() {
        WanxImageModel wanxImageModel = WanxImageModel
                .builder()
                .modelName("wanx2.1-t2i-turbo")
                .apiKey(System.getenv("WANX.API_KEY"))
                .build();
        Response<Image> response = wanxImageModel.generate("");
        URI url = response.content().url();
        System.out.println(url);
    }
}
