package com.mifinity.creditcardapp.service;

/**
 * UserSecurityService is an interface  which consists of method declarations to check logged in user
 * @author Akarsh Jain
 * @version 1.0
 */
public interface UserSecurityService {

	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
}
