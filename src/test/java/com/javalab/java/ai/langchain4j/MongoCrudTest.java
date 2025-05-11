package com.javalab.java.ai.langchain4j;

import com.javalab.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void testInsert() {
//       mongoTemplate.insert(new ChatMessages(1L,"聊天记录"));
//    }

    @Test
    public void testInsert2() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testFindById() {
        ChatMessages byId = mongoTemplate.findById("682075c3dbea2f3e7fc38396", ChatMessages.class);
        System.out.println(byId);
    }

    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("_id" ).is("6820764d2f13d828994980d4" );
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content" , "聊天记录列表2");
        mongoTemplate.upsert(query, update, ChatMessages.class);
    }

    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id" ).is("6820770222a4552e31c1c6bc" );
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
