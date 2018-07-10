package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Author;
import com.crossover.techtrial.service.AuthorService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private WebClient webClient;

    @MockBean
    private AuthorService authorService;

    private List<Author> authors = asList(new Author(), new Author());

    //-------------------------------------------------------------------------
    @Before
    public void init(){
        when(authorService.findAll()).thenReturn(authors);
        webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
                .useMockMvcForHosts("authors.com", "myauthors.org")
                .build();

    }
//--------------------------------------------------------------------------


    @Test
    public void getAllNames() {
    }
}