package com.mifinity.creditcardapp;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mifinity.creditcardapp.model.Role;
import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.repository.RoleRepository;
import com.mifinity.creditcardapp.repository.UserRepository;
import com.mifinity.creditcardapp.service.impl.UserServiceImpl;
/**
 * The UserServiceImplTest class test create new user functionality.
 * @author Akarsh Jain
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	private User user;
	private User savedUser;
	private List<Role> roles;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Before
	public void setUp() {
		user = new User();
		user.setUsername("akarsh");
		user.setPassword("pass");
		savedUser = new User();
		savedUser.setUsername("akarsh");
		savedUser.setPassword(bCryptPasswordEncoder.encode("pass"));
		roles = new ArrayList<>();
		roles.add(new Role("ROLE_USER"));
		when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("qazxcdfvgcds");
	}
	
	@Test
	public void createUserTest() {
		when(userRepository.save(user)).thenReturn(savedUser);
		assertTrue(userServiceImpl.createUser(user));
		verify(userRepository, times(1)).save(user);
	}

}
