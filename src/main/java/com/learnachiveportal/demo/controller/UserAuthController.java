package com.learnachiveportal.demo.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.jwt.AthenticationService;
import com.learnachiveportal.demo.jwt.JwtHelper;
import com.learnachiveportal.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	private UserService userService;

	private final JwtHelper jwtHelper = new JwtHelper();

	private final AthenticationService authAthenticationService = null;

	// Create a new user according to their role

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserRquestDto userRequest) {
		return userService.createUser(userRequest);
	}

	// Genrate token with manually give email and password

	@PostMapping("/login")
	public JwtResponse login(@RequestBody JwtRequest request) {
		return userService.login(request);
	}

	// genrate token with valid login user

	@GetMapping("/loginwithuser")
	public JwtResponse loginWithTheUser(@RequestBody JwtRequest jwtRequest) throws UserPrincipalNotFoundException {
		return userService.loginWithUserDetails(jwtRequest);

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) {
		UserDetails authenticatedUser = authAthenticationService.authenticate(jwtRequest);
		String jwtToken = jwtHelper.generateToken(authenticatedUser);
		JwtResponse loginResponse = new JwtResponse().setJwtToken(jwtToken).setExpiresIn(jwtHelper.getExpirationTime());
		return ResponseEntity.ok(loginResponse);
	}
}
