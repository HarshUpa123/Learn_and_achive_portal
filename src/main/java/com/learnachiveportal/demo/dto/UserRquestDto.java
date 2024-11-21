package com.learnachiveportal.demo.dto;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
