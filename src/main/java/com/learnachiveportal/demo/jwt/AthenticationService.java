package com.learnachiveportal.demo.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.user.UserRepository;

public class AthenticationService {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private AuthenticationManager authenticationManager;

	public AthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User authenticate(JwtRequest input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}
}
