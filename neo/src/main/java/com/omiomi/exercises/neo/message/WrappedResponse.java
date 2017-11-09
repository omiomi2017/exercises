package com.omiomi.exercises.neo.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class used to store a response that server will send to JSON client
 * 
 * @author omi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WrappedResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String status;

	public WrappedResponse() {
	}

	public WrappedResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
