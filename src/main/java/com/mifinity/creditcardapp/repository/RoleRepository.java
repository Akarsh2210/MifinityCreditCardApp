package com.mifinity.creditcardapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifinity.creditcardapp.model.Role;
/**
 * UserRepository interface is the repository that extends the JPA repository.
 * It is connected to "role" table in the database 
 * 
 * @author Akarsh Jain
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
