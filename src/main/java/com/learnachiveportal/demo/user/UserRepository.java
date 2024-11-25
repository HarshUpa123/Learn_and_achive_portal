package com.learnachiveportal.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learnachiveportal.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user WHERE userName = :userName LIMIT 1", nativeQuery = true)
	Optional<User> findByUserName(@Param("userName") String userName);


}
