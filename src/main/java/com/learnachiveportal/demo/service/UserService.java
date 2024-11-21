package com.learnachiveportal.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.ResponseEntity;

import com.learnachiveportal.demo.dto.ApiResponse;
import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.User;

public interface UserService {

	public ResponseEntity<ApiResponse> createUser(UserRquestDto dto);

	public JwtResponse loginWithUserDetails(JwtRequest request) throws UserPrincipalNotFoundException;

	public User updateUserDetails(UserRquestDto userdto, Long userId);
	
	public void deleteById(Long id);

}
