package com.mifinity.creditcardapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mifinity.creditcardapp.ApplicationUtil;
import com.mifinity.creditcardapp.model.Role;
import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.repository.RoleRepository;
import com.mifinity.creditcardapp.repository.UserRepository;
import com.mifinity.creditcardapp.service.UserService;

/**
 * UserServiceImpl is a class that extends an interface ISecurityService 
 * and overrides all the methods present in it. 
 * 
 * @author Akarsh Jain
 * @version 1.0
 */


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * This method is used to create a new user.
	 * 
	 * @param user contains user details
	 * 
	 * @return true if the user is added.
	 */
	@Override
	public boolean createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName(ApplicationUtil.ROLE_USER);
		user.addRole(userRole);
		userRepository.save(user);
		return true;

	}

	/*
	 * This method is used to find a user.
	 * 
	 * @param username contains user name.
	 * 
	 * @return the user name.
	 * 
	 */
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
