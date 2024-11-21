package com.learnachiveportal.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learnachiveportal.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
//	Optional<User> findByEmail(String email);
	
//	  @Query(value = "SELECT * FROM user WHERE useremail = :email", nativeQuery = true)
//	    Optional<User> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM user WHERE useremail = :email LIMIT 1", nativeQuery = true)
	Optional<User> findByEmail(@Param("email") String email);

	  
	  
	}

