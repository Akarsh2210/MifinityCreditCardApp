package com.mifinity.creditcardapp.service;

import com.mifinity.creditcardapp.model.User;
/**
 * UserService is an interface  which consists of method declarations to find user
 * @author Akarsh Jain
 * @version 1.0
 */
public interface UserService {

	boolean createUser(User user);
	User findByUsername(String username);
}
