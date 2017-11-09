package com.omiomi.exercises.neo.message;

/**
 * Class used to communicate API key update
 * Java <3 boilerplate!
 * @author omi
 *
 */
public class NewAPIKEY {
	private String apiKey;
	public NewAPIKEY() {}
	public NewAPIKEY(String newApiKey) {
		setApiKey(newApiKey);
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String newKey) {
		this.apiKey = newKey;
	}
}
