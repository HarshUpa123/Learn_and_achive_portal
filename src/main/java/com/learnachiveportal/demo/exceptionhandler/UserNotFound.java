package com.learnachiveportal.demo.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFound extends RuntimeException {

	private String msg;
	private int statusCode;

	public UserNotFound(String msg, int statusCode) {
		super(String.format("Status Code %d: User not found - %s", statusCode, msg));
		this.msg = msg;
		this.statusCode = statusCode;
	}

}
