package com.learnachiveportal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnachiveportal.demo.entity.Role;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.service.UserService;
import com.learnachiveportal.demo.serviceImpl.UserServiceImpl;
import com.learnachiveportal.demo.user.RoleRepository;
import com.learnachiveportal.demo.user.UserRepository;

@RestController
@RequestMapping("usercontroller")
public class UserController {

	@Autowired
	UserService userService;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;

	  public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	  
//	    @GetMapping("/register")
//	    public String showRegistrationForm() {
//	        return "register";
//	    }
	    
	    
//	    @PostMapping("/register")
//	    public String registerUser(@ModelAttribute User user) {
//	        user.setPassword(passwordEncoder.encode(user.getPassword()));
//	        Role userRole = roleRepository.findByName("ROLE_USER");
//	        user.getRoleOfUser().add(userRole);
//	        userRepository.save(user);
//	        return "redirect:/login";
//	    }

//	    @GetMapping("/login")
//	    public String showLoginForm() {
//	        return "login";
//	    }
	
//	@PostMapping("/saveUser")
//	public User saveUserDetails(@RequestBody User user) {
//		User userDetails = userService.saveUser(user);
//		return userDetails;
//	}
}
