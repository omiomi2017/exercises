package com.omiomi.exercises.neo.config;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Reads configuration from resources/application.properties for the rest client
 */

@Configuration
@ComponentScan(basePackages = "com.omiomi.exercises.neo" )
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 
	 * @return factory with updated timeouts
	 */
	@Bean
	@ConfigurationProperties(prefix = "neo.rest")
	public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
		// Pulls in config values for connection-request-timeout, connect-timeout,
		// read-timeout from application.properties
		return new HttpComponentsClientHttpRequestFactory();
	}

	@Bean
	public RestTemplate customRestTemplate() {
		return new RestTemplate(customHttpRequestFactory());
	}

	@Value("${neo.api.API_KEY}")
	@NotNull
	private String configApiKey;

	/**
	 * Get API_KEY to use.
	 * 
	 * @return api key to use. Environment variable API_KEY overrides the
	 *         neo.api.API_KEY
	 */
	@Bean
	public String getAPIKey() {
		// check if API key was specified via environment variable.
		String envApiKey = env.getProperty("API_KEY");
		return envApiKey != null ? envApiKey : configApiKey;
	}

	@Value("${neo.api.BASE_URL}")
	@NotNull
	private String configApiBaseUrl;

	/**
	 * 
	 * @return String containing API base
	 */
	@Bean
	public String getApiBaseURL() {
		return configApiBaseUrl;
	}
	
	@Value("${neo.api.max_days_per_request}")
	@NotNull
	private int configMaxDaysPerRequest;

	/**
	 * Get largest interval in days per API request.
	 * @return int with number of days
	 */
	@Bean
	public int getApiMaxDaysPerRequest() {
		return configMaxDaysPerRequest;
	}
	
	
}