package com.mifinity.creditcardapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifinity.creditcardapp.model.User;
/**
 * UserRepository interface is the repository that extends the JPA repository.
 * It is connected to "user" table in the database 
 * 
 * @author Akarsh Jain
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
