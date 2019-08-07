package com.mifinity.creditcardapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.service.UserSecurityService;
import com.mifinity.creditcardapp.service.UserService;
import com.mifinity.creditcardapp.validate.UserValidator;
/**
 * The UserController class contains methods 
 * which  handles the HTTP request and response related to user login.
 * @author Akarsh Jain
 * @version 1.0
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserSecurityService userSecurityService;

	/**
	 * This method is for displaying the login page
	 * @param model
	 * @param error contains info if there is an error
	 * @param logout is for checking logout request and throwing message
	 * @return It returns the login page after validation
	 */
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	/**
	 * This method is for registration of new user
	 * @param model stores the form structure 
	 * @return It returns registration page
	 */
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("registrationForm", new User());
		return "registration";
	}

	/**
	 * This method takes details of user to register a new user
	 *  and store it in a database
	 * @param registrationForm is user object that contains username, password and passwordConfirm
	 * @return creditCardFunctions page after validating the form
	 */
	@PostMapping("/registration")
	public String registration(@ModelAttribute("registrationForm") User registrationForm, BindingResult bindingResult) {
		userValidator.validate(registrationForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		if (userService.createUser(registrationForm)) {
			userSecurityService.autoLogin(registrationForm.getUsername(), registrationForm.getPasswordConfirm());
			return "redirect:/creditCardFunctions";
		} else {
			return "error";
		}

		

	}



}
