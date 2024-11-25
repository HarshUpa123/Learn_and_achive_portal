package com.learnachiveportal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.exceptionhandler.UserNotFound;
import com.learnachiveportal.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserRquestDto userRequest) {
		System.out.print("============================================"+userRequest);
		return userService.createUser(userRequest);
	}

	@GetMapping("/loginwithuser")
	public JwtResponse loginWithTheUser(@RequestBody JwtRequest jwtRequest) throws UserNotFound {
		System.out.print("============================================"+jwtRequest);

		return userService.loginWithUserDetails(jwtRequest);

	}

	@PutMapping("/updateUser/{id}")
	public User updateUserDetails(@RequestBody UserRquestDto userdto, @PathVariable Long id) {
		return userService.updateUserDetails(userdto, id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@RequestParam Long id) {
		userService.deleteById(id);

	}
}
