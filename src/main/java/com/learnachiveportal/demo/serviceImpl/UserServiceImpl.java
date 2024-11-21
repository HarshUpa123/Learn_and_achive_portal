package com.learnachiveportal.demo.serviceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnachiveportal.demo.configure.MyConfig;
import com.learnachiveportal.demo.dto.ApiResponse;
import com.learnachiveportal.demo.dto.JwtRequest;
import com.learnachiveportal.demo.dto.JwtResponse;
import com.learnachiveportal.demo.dto.UserRquestDto;
import com.learnachiveportal.demo.entity.Role;
import com.learnachiveportal.demo.entity.User;
import com.learnachiveportal.demo.jwt.JwtHelper;
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
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtHelper helper;

	@Autowired
	MyConfig myConfig;

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
						.body(ApiResponse.builder().msj("Invailid Role").statusCode(400L).build());
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
				.body(ApiResponse.builder().msj("User created").statusCode(200L).data(map).build());
	}

	@Override
	public JwtResponse loginWithUserDetails(JwtRequest request) throws UserPrincipalNotFoundException {
		UserDetails userByUsername = null;
		try {
			userByUsername = loadUserByUsername(request.getEmail());
			if (passwordEncoder.matches(request.getPassword(), userByUsername.getPassword())) {
				String token = this.helper.generateToken(userByUsername);

				JwtResponse userResponse = JwtResponse.builder().jwtToken(token).userName(userByUsername.getUsername())
						.build();
				return userResponse;
			}
		} catch (Exception e) {
			throw new UserPrincipalNotFoundException("User is not valid");
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
		Set<SimpleGrantedAuthority> authorities = user.getRoleName().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

	}

	@Override
	public User updateUserDetails(UserRquestDto userdto, Long userId) {
		
		User user= userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with this id: " + userdto.getUserName()));
		 User user1= new User();
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
