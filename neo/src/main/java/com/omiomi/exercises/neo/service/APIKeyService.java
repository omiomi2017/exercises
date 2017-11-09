package com.omiomi.exercises.neo.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.omiomi.exercises.neo.config.AppConfig;

/**
 * Class is used to update API key
 * This is really to just update the value of a field that would be passed to query NASA API
 * For sure not an example of any security considerations!!!
 * @author omi
 *
 */

@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class APIKeyService {
	
	@Autowired
	private AppConfig config;
	
	private Lock lock;
	private String apiKey;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Default constructor
	 */
	public APIKeyService() {
		lock = new ReentrantLock();
	}
	
	/**
	 * returns API KEY
	 * @return API KEY
	 */
	public String getApiKey() {
		return apiKey!=null ? apiKey : config.getAPIKey();
	}
	
	/**
	 * Set Api Key should be alphanum upper/lowercase less than 64 char
	 * @param newKey new key
	 * @return true if operation completed successfully.
	 */
	public boolean setApiKey(String newKey) {
		//key seems to be alphanum upper/lowercase + digits and possibility of underscore for special keys
		//not sure exactly on the specifics of their keys, but it seems around 40 chars, may vary.
		if (!newKey.matches("\\w+") || newKey.length()>64) {
			logger.error("Could not update API key to {}", newKey );
			return false;
		}
		boolean ret=false;
		lock.lock();
		try {
			this.apiKey = newKey;
			logger.info("Updated api key to {}", this.apiKey);
			ret=true;
		} finally {
			lock.unlock();
		}
		return ret;
	}
}
