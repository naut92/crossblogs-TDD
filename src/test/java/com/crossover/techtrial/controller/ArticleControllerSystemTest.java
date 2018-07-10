package com.crossover.techtrial.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleControllerSystemTest {
    //@Autowired
    //private ArticleController articleController;

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
    public void allArticleFromDatabaseAreAvalableOnWeb() throws Exception {
        this.mockMvc.perform(get("/articles/1")
                .accept(MediaType.asMediaType(MediaType.APPLICATION_JSON)))//parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                //.andExpect(content().mimeType(MediaType.APPLICATION_JSON))
                //.andExpect(content().contentType("text/html;charset=UTF-8"))//если есть фронт на html
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(allOf(
                        containsString("{\"id\":1,\"email\":\"john@publisher.com\",\"title\":\"How to boil an egg\",\"content\":\"Get a bowl and heat the water to reach boiling point. Put chicken egg for approximately 10 minutes.\",\"date\":null,\"published\":null}"),
                        containsString("iuhoi\":hibibo/iuojuji797 ")
                )));
    }
}