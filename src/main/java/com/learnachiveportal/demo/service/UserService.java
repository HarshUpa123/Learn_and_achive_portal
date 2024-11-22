package com.learnachiveportal.demo.service;

import org.springframework.http.ResponseEntity;

import com.learnachiveportal.demo.dto.ApiResponse;
import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.exceptionhandler.UserNotFound;

public interface UserService {

	public ResponseEntity<ApiResponse> createUser(UserRquestDto dto);

	public JwtResponse loginWithUserDetails(JwtRequest request) throws UserNotFound;

	public User updateUserDetails(UserRquestDto userdto, Long userId);

	public void deleteById(Long id);

}
