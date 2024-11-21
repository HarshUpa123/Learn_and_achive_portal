package com.learnachiveportal.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {

	String jwtToken;
	String userName;

	public String jwtToken() {
		return jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public JwtResponse setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
		return this;
	}

}
