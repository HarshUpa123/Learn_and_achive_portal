package com.learnachiveportal.demo.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learnachiveportal.demo.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ApiResponse> userNotFoundException(UserNotFound ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, 404);
		return new ResponseEntity<ApiResponse>(apiResponse, null, 404);
	}

}
