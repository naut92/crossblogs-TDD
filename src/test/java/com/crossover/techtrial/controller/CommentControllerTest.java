package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    @Autowired
    private TestRestTemplate template;

    private static final String URI = "/articles/";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createComment() {
        Long articleId = 1L;
        ResponseEntity<Article> responseEntity = template.getForEntity(URI + "{id}", Article.class, articleId);
        int status = responseEntity.getStatusCodeValue();
        Article resultArticle = responseEntity.getBody();
        HttpEntity<Object> comment = getHttpEntity("{\"email\": \"user6@gmail.com\", \"message\": \"comm_message5\" }");

        ResponseEntity<Comment> resultAsset = template.postForEntity(URI + resultArticle.getId() + "/comments", comment, Comment.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    @Test
    public void getComments() {
        Long artcleId = 1L;
        ResponseEntity<List<Comment>> commentResponse = template.exchange(URI + artcleId + "/comments", HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {});
        List<Comment> commentList = commentResponse.getBody();
        boolean flag = false;
        int count = 0;
        for (Comment comment : commentList){
            if(comment.getId() != null) {
                count++;
                flag = true;
            }
        }
        Assert.assertTrue("find " + count + " comment", flag);
        Assert.assertNotNull(commentList);
    }


    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}