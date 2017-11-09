package com.omiomi.exercises.neo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.service.APIKeyService;

public class APIKeyServiceTest extends AbstractTest{
	
	@Autowired
	private APIKeyService apiKeyService;
	
	@Test
	public void apiKeyServiceLoaded() {
		assertNotNull(apiKeyService);
	}
	
	@Test
	public void apiKeyUpdated() {
		String oldKey = apiKeyService.getApiKey();
		String setToThisKey="NEWKEY";
		apiKeyService.setApiKey(setToThisKey);
		String newKey = apiKeyService.getApiKey();
		assertNotEquals(oldKey, newKey);
		assertEquals(newKey, setToThisKey);
	}
	
	/**
	 * Test for characters encoding, would be used in SQL injections and the likes.
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void failOnEncodedCharacters() throws UnsupportedEncodingException {
		String oldValue = apiKeyService.getApiKey();
		String encodedChars = URLEncoder.encode("chars^ should be Encoded!", "UTF-8");
		boolean success = apiKeyService.setApiKey(encodedChars);
		assertFalse(success);
		assertEquals(oldValue, apiKeyService.getApiKey());
	}
	
	/**
	 * Long strings as keys should fail.
	 */
	@Test
	public void failOnLongChars() {
		String oldValue = apiKeyService.getApiKey();
		String newKey = "alsdkjflaksdjflkajsdflkajsdlfkjasldkfjalskdjflakjdflakjsdflkjasdlfkjalsdfk";
		boolean success = apiKeyService.setApiKey(newKey);
		assertFalse(success);
		assertEquals(oldValue, apiKeyService.getApiKey());
	}
	
	
}
