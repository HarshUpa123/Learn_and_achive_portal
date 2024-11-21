package com.learnachiveportal.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnachiveportal.demo.entity.Role;
import com.learnachiveportal.demo.entity.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
