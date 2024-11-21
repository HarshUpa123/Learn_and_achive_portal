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

    private long expiresIn;

    public String jwtToken() {
        return jwtToken;
    }

    // Getter and Setter for jwtToken
    public String getJwtToken() {
        return jwtToken;
    }

    public JwtResponse setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this; // Return the current instance for method chaining
    }

    // Getter and Setter for expiresIn
    public long getExpiresIn() {
        return expiresIn;
    }

    public JwtResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this; // Return the current instance for method chaining
    }
}


