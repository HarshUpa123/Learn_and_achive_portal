package com.learnachiveportal.demo.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

	public User(String email2, String password2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "useremail")
	private String email;

	@Column(name = "userpassword")
	private String password;

	@JsonIgnoreProperties(value = "userRole")
	@ManyToMany
	private Set<Role> roleName = new HashSet<>();
	
	@Override
	public String getUsername() {
		return userName;
	}
	
	 @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", email='" + email + '\'' +
	                ", username='" + userName + '\'' +
	                ", roles=" + roleName.stream().map(Role::getName).collect(Collectors.toSet()) + // Avoid infinite recursion
	                '}';
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
