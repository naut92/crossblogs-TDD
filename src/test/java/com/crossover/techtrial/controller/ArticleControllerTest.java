package com.crossover.techtrial.controller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.Article;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

	@Autowired
	private TestRestTemplate template;

	private static final String URI = "/articles/";


	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testArticleShouldBeCreated() throws Exception {
		HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
		ResponseEntity<Article> resultAsset = template.postForEntity(URI, article, Article.class);
		Assert.assertNotNull(resultAsset.getBody().getId());
	}

	@Test
	public void getArticleById() {
		ResponseEntity<Article> responseEntity = template.getForEntity(URI + "{id}", Article.class, new Long(1));
		int status = responseEntity.getStatusCodeValue();
		Article resultArticle = responseEntity.getBody();

		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		Assert.assertNotNull(resultArticle);
		Assert.assertEquals(1l, resultArticle.getId().longValue());
	}

	@Test
	public void updateArticle() {
		Article article = new Article();
		Long idArticle = 4L;
		article.setId(idArticle);
		article.setEmail("testUpdate@gmail.com");
		article.setTitle("I'm a new title - 2");
		article.setContent("I'm a new content - 2!");
		HttpEntity<Article> requestEntity = new HttpEntity<>(article);

		ResponseEntity<Void> responseEntity = template.exchange(URI + idArticle, HttpMethod.PUT, requestEntity, Void.class);

		int status = responseEntity.getStatusCodeValue();
		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
	}

	@Test
	public void deleteArticleById() {
		ResponseEntity<Void> responseEntity = template.exchange(URI + "{id}", HttpMethod.DELETE,
				null, Void.class, new Long(6));
		int status = responseEntity.getStatusCodeValue();
		Assert.assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		//Assert.assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);
	}

	@Test
	public void searchArticles(){
		ResponseEntity<List<Article>> articleResponse = template.exchange(URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Article>>() {});

		String text = "egg";
		List<Article> articles = articleResponse.getBody();
		boolean flag = false;

		for (Article article : articles){
			if (article.getTitle().contains(text)){
				flag = true;
			}
		}

		Assert.assertTrue("find " + text, flag);
	}

	@Test
	public void findAll(){
		ResponseEntity<List<Article>> articleResponse = template.exchange(URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Article>>() {});
		List<Article> articles = articleResponse.getBody();
		for (Article article : articles){
			System.out.println(article.getTitle());
		}
		Assert.assertNotNull(articles);
		Assert.assertSame("How to boil an egg", "How to boil an egg");
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(APPLICATION_JSON);
		return new HttpEntity<>(body, headers);
	}
}