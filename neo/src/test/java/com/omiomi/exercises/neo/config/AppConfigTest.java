package com.omiomi.exercises.neo.config;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.config.AppConfig;

public class AppConfigTest extends AbstractTest {
	@Autowired
	AppConfig config;

	@Test
	public void configNotNull() {
		assertNotNull(config);
	}

	// Expecting a valid URL specified in the config for API BASE
	@Test
	public void API_BaseUr_isValidURI() throws URISyntaxException {
		assertNotNull(new URI(config.getApiBaseURL()));
	}

}
