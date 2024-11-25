package com.learnachiveportal.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRquestDto {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	  	private Long id;
	    private String userName;
	    private String email;
	    private String password;
	    private Set<String> roles;
}
