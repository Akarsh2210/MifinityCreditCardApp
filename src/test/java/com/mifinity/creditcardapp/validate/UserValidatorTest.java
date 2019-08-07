package com.mifinity.creditcardapp.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.service.UserService;
/**
 * The UserValidatorTest class test validations related to  user login.
 * @author Akarsh Jain
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
	
	private User user;
	private BindingResult bindingResult;
	
	@InjectMocks
	private UserValidator userValidator;
	
	@Mock
	private UserService userService;
	
	@Before
	public void setUp() {
		user = new User();
		bindingResult = new BeanPropertyBindingResult(user, "user");
	}
	
	@Test
	public void validateSuccessTest() {
		user.setUsername("akarsh");
		user.setPassword("pass1");
		user.setPasswordConfirm("pass1");
		userValidator.validate(user, bindingResult);
		assertFalse(bindingResult.hasErrors());
	}
	
	@Test
	public void validateFieldsNullFailTest() {
		userValidator.validate(user, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("username") );
		assertNotNull( bindingResult.getFieldError("password") );
		assertNotNull( bindingResult.getFieldError("passwordConfirm") );
	}
	
	@Test
	public void validatePasswordDifferentFailTest() {
		user.setUsername("akarsh");
		user.setPassword("pass1");
		user.setPasswordConfirm("pass2");
		userValidator.validate(user, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("passwordConfirm") );
	}
	
	@Test
	public void validateUsernameDuplicateFailTest() {
		user.setUsername("akarsh");
		user.setPassword("pass1");
		user.setPasswordConfirm("pass1");
		when(userService.findByUsername(Mockito.anyString())).thenReturn(new User());
		userValidator.validate(user, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("username") );
		verify(userService, times(1)).findByUsername(Mockito.anyString());
	}

}
