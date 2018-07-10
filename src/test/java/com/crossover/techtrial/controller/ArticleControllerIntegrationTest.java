package com.crossover.techtrial.controller;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private WebClient webClient;

    @MockBean
    private ArticleService articleService;

    private List<Article> articles = asList(new Article(), new Article());

//-------------------------------------------------------------------------
    @Before
    public void init(){
        when(articleService.findAll()).thenReturn(articles);
        webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
                .useMockMvcForHosts("articles.com", "myarticles.org")
                .build();

    }
//--------------------------------------------------------------------------
    @Test
    public void requestForArticlesIsSuccessfullyProcessWhitAvalableArticleList() throws Exception {
        this.mockMvc.perform(get("/articles/")
                .accept(MediaType.asMediaType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                //.andExpect(content().mimeType(MediaType.APPLICATION_JSON))
                //.andExpect(content().contentType("text/html;charset=UTF-8"))//если есть фронт на html
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(allOf(
                        containsString("{\"id\":1,\"email\":\"john@publisher.com\",\"title\":\"How to boil an egg\",\"content\":\"Get a bowl and heat the water to reach boiling point. Put chicken egg for approximately 10 minutes.\",\"date\":null,\"published\":null}"),
                        containsString("iuhoi\":hibibo/iuojuji797 ")
                )));
    }

//--------------------------------------------------------------------------
    @Test
    public void articlesPageContentIsRenderAsHtmlOrJsonWhithListOfArticles() throws IOException {
        //HtmlPage page = webClient.getPage("http://articles.com/article.html");
        HtmlPage page = webClient.getPage("http://localhost:8080/articles/1/");
        List<String> articlesList = page.getElementsByTagName("li")
                .stream().map(DomNode::asText).collect(Collectors.toList());
        assertThat(articlesList, hasItems("\"id\":1,\"email\":\"john@publisher.com\"", "id\":4,\"email\":\"testUpdate@gmail.com\""));
    }
}