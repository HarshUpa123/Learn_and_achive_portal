package com.learnachiveportal.demo.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.learnachiveportal.demo.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnachiveportal.demo.dto.ApiResponse;
import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.Role;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.exceptionhandler.UserNotFound;
import com.learnachiveportal.demo.service.UserService;
import com.learnachiveportal.demo.user.RoleRepository;
import com.learnachiveportal.demo.user.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;


	JwtResponse storeduserDetails = new JwtResponse();

	public boolean verifyPassword(String inputPassword, String storedHashedPassword) {
		return passwordEncoder.matches(inputPassword, storedHashedPassword);
	}

	@Override
	public ResponseEntity<ApiResponse> createUser(UserRquestDto userRequest) {
		System.out.println("===============9000000======================" + userRequest);
		Set<Role> roles = new HashSet<>();
		for (String roleName : userRequest.getRoles()) {
			Role role = roleRepository.findByName(roleName);
			if (role != null) {
				roles.add(role);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(ApiResponse.builder().msg("Invailid Role").statusCode(400).build());
			}
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(encryptedPassword);
		user.setRoleName(roles);
		User save = userRepository.save(user);
		Map<String, Object> map = new HashMap<>();
		map.put("USER", save);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.builder().msg("User created").statusCode(200).build());
	}

	@Override
	public JwtResponse loginWithUserDetails(JwtRequest request) throws UserNotFound {
		UserDetails userByUsername = null;
		userByUsername = loadUserByUsername(request.getUserName());
		if (passwordEncoder.matches(request.getPassword(), userByUsername.getPassword())) {

			JwtResponse userResponse = JwtResponse.builder().jwtToken(new JwtUtils().generateToken(userByUsername.getUsername())).userName(userByUsername.getUsername())
					.build();
			System.out.println("================================91================="+userResponse);
			return userResponse;

		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFound("", 404));
		Set<SimpleGrantedAuthority> authorities = user.getRoleName().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

	}

	@Override
	public User updateUserDetails(UserRquestDto userdto, Long userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("", 404));
		User user1 = new User();
		user1.setUserName(user.getUsername());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		return user1;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
