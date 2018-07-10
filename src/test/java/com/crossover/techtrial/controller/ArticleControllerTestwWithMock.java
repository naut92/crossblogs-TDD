package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTestwWithMock {

    @Mock
    private ArticleService articleService;

    private ArticleController controller;

    private MockMvc mockMvc;

    private List<Article> articles = asList(new Article(), new Article());

    @Before
    public void setUp() throws Exception {
        controller = new ArticleController(articleService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(articleService.findAll()).thenReturn(articles);
    }

    @Test
    public void createArticle() {
        Model model = new ExtendedModelMap();
        ModelMap modelMap = new ExtendedModelMap();
        ModelAndView modelAndView = new ModelAndView();
        Article article = new Article();
        assertThat(controller.createArticle(article), equalTo(""));
        //assertThat(model.asMap(), hasEntry("articles", articles));
    }

    @Test
    public void getArticleById() {
    }

    @Test
    public void updateArticle() {
    }

    @Test
    public void deleteArticleById() {
    }

    @Test
    public void searchArticles() {
    }

    @Test
    public void findAll() {
    }
}