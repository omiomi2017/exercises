package com.omiomi.exercises.neo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.config.AppConfig;

/**
 * Integration test for HomeController - test if the page returns 200 status and familiar content
 * @author omi
 *
 */
@SpringBootTest(classes = AppConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeControllerTest extends AbstractTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void indexPageLoads() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/",String.class);
		assertEquals(response.getStatusCodeValue(),200);
		assertThat(response.getBody()).contains("Near Earth Objects Exercise");
	}
}
