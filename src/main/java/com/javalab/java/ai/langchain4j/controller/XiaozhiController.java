package com.javalab.java.ai.langchain4j.controller;

import com.javalab.java.ai.langchain4j.assistant.XiaozhiAgent;
import com.javalab.java.ai.langchain4j.bean.ChatForm;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Base64;

@Tag(name = "硅谷小智")
@RestController
@RequestMapping("/xiaozhi")
@Slf4j
public class XiaozhiController {

    @Autowired
    private XiaozhiAgent xiaozhiAgent;

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Value("classpath:static/images/1.png")
    private Resource resource;

    @Operation(summary = "硅谷小智对话")
    @PostMapping( value = "/chat",produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return xiaozhiAgent.chat(chatForm.getMemoryId(),chatForm .getMessage());
    }

    @Operation(summary = "获取图片")
    @PostMapping(value = "/image/call")
    public String readImage(@RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        byte[] byteArray = file.getBytes();
        String base64Data = Base64.getEncoder().encodeToString(byteArray);

        UserMessage userMessage = UserMessage.from(
                TextContent.from("获取图片分析图片的症状该挂什么科室"),
                ImageContent.from(base64Data, file.getContentType())
        );
        ChatResponse response = chatLanguageModel.chat(userMessage);
        System.out.println(response.aiMessage().text());
        return response.aiMessage().text();
    }
}
