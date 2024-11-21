package com.learnachiveportal.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnachiveportal.demo.dto.ApiResponse;
import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.Role;
import com.learnachiveportal.demo.entity.User;

@Service
public interface UserService {

	public User saveUser(User user);

	public Role findByName(String user);

	public boolean verifyPassword(String inputPassword, String storedHashedPassword);

	public ResponseEntity<ApiResponse> createUser(UserRquestDto dto);

	public JwtResponse login(JwtRequest request);

	public JwtResponse loginWithUserDetails(JwtRequest request) throws UserPrincipalNotFoundException;
	
//	public JwtResponse authenticateUser(JwtRequest jwtRequest);

}
