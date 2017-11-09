package com.omiomi.exercises.neo.controller;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.config.AppConfig;
import com.omiomi.exercises.neo.message.NewAPIKEY;
import com.omiomi.exercises.neo.message.WrappedResponse;
import com.omiomi.exercises.neo.service.APIKeyService;

/**
 * Test APIKeyController
 * @author omi
 *
 */
@SpringBootTest(classes = AppConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class APIKeyControllerTest extends AbstractTest{
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private APIKeyService apiKeyService;
	
	private final String validKeyDemo="DEMO_USER";
	private final String validChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	private final String invalidKey="Ajf0@0ajd,_ )alij3liha";
	private final String invalidKeyLong = "TQ5YagM4F8B8PCz5xtnaTQ5YagM4F8B8PCz5xtnaTQ5YagM4F8B8PCz5xtna12345";
	
	@Before
	public void setup() {
		when(this.apiKeyService.setApiKey(validKeyDemo)).thenReturn(true);
		when(this.apiKeyService.setApiKey(validChars)).thenReturn(true);
		when(this.apiKeyService.setApiKey(invalidKey)).thenReturn(false);
		when(this.apiKeyService.setApiKey(invalidKeyLong)).thenReturn(false);
	}
	
	
	@Test
	public void testValidKeyDemo() {
		HttpEntity<NewAPIKEY> request = new HttpEntity<>(new NewAPIKEY(validKeyDemo));
		WrappedResponse response = restTemplate.postForObject("/apikey", request, WrappedResponse.class);
		assertNotNull(response);
		assertTrue(response.getStatus().equals("success"));
	}
	
	@Test
	public void testValidChars() {
		HttpEntity<NewAPIKEY> request = new HttpEntity<>(new NewAPIKEY(validChars));
		WrappedResponse response = restTemplate.postForObject("/apikey", request, WrappedResponse.class);
		assertNotNull(response);
		assertTrue(response.getStatus().equals("success"));
	}
	
	@Test
	public void testInvalidKey() {
		HttpEntity<NewAPIKEY> request = new HttpEntity<>(new NewAPIKEY(invalidKey));
		WrappedResponse response = restTemplate.postForObject("/apikey", request, WrappedResponse.class);
		assertNotNull(response);
		assertTrue(response.getStatus().equals("failure"));
	}
	
	@Test
	public void testInvalidKeyLong() {
		HttpEntity<NewAPIKEY> request = new HttpEntity<>(new NewAPIKEY(invalidKeyLong));
		WrappedResponse response = restTemplate.postForObject("/apikey", request, WrappedResponse.class);
		assertNotNull(response);
		assertTrue(response.getStatus().equals("failure"));
	}
	
}
