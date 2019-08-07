package com.mifinity.creditcardapp.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.service.UserService;

/**
 * UserValidator is a class that validates the user log in details.
 * 
 * @author Akarsh Jain
 * @version 1.0
 */
@Component
public class UserValidator {

	@Autowired
	private UserService userService;

	public void validate(Object target, Errors errors) {

		User user = (User) target;

		if (user.getUsername() == null || "".equals(user.getUsername().trim())) {
			errors.rejectValue("username", "NotEmpty");
		} else if (user.getUsername().length() < 5 || user.getUsername().length() > 20) {
			errors.rejectValue("username", "Size.registrationForm.username");
		}
		
		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			errors.rejectValue("password", "NotEmpty");
		} else if (user.getPassword().length() < 5 || user.getPassword().length() > 15) {
			errors.rejectValue("password", "Size.registrationForm.password");
		}

		
		if (user.getPasswordConfirm() == null || "".equals(user.getPasswordConfirm().trim())) {
			errors.rejectValue("passwordConfirm", "NotEmpty");
		} else if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.registrationForm.passwordConfirm");
		}

		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.registrationForm.username");
		}
	}

}
