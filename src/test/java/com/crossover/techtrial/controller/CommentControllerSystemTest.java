package com.crossover.techtrial.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerSystemTest {
    @Autowired
    private ArticleController articleController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    //-------------------------------------------------------------------------
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
//-------------------------------------------------------------------------


    @Test
    public void allCommentsMayBeRetrived() throws Exception {
        mockMvc.perform(get("/articles/1/comments"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"email\":\"john@publisher.com\",\"message\":\"comment_mess\",\"date\":null},{\"id\":2,\"email\":\"john@publisher.com\",\"message\":\"comment_mess1\",\"date\":null},{\"id\":3,\"email\":\"testUpdate4@gmail.com\",\"message\":\"comment_mess2\",\"date\":null},{\"id\":4,\"email\":\"testUpdate3@gmail.com\",\"message\":\"comment_mess21\",\"date\":null},{\"id\":5,\"email\":\"testUpdate3@gmail.com\",\"message\":\"comment_mess2\",\"date\":null},{\"id\":6,\"email\":\"user6@gmail.com\",\"message\":\"comm_message5\",\"date\":null},{\"id\":7,\"email\":\"user6@gmail.com\",\"message\":\"comm_message5\",\"date\":null}]"));

    }
}