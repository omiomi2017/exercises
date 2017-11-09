package com.omiomi.exercises.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omiomi.exercises.neo.message.NewAPIKEY;
import com.omiomi.exercises.neo.message.WrappedResponse;
import com.omiomi.exercises.neo.service.APIKeyService;

import org.springframework.context.annotation.ScopedProxyMode;

@RestController
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class APIKeyController {
	
	private APIKeyService apiKeyService;
	
	/**
	 * DI Constructor
	 * @param apiKeyService apiKeyService
	 */
	
	@Autowired
	APIKeyController(APIKeyService apiKeyService){
		this.apiKeyService = apiKeyService;
	}
	
	/**
	 * Set API Key
	 * @return success or failure of operation
	 */
	@RequestMapping(value="/apikey", method=RequestMethod.POST)
	@ResponseBody
	public WrappedResponse setAPIKey(@RequestBody NewAPIKEY newKey){
    		return new WrappedResponse(apiKeyService.setApiKey(newKey.getApiKey()) ? "success":"failure");
    }
}
