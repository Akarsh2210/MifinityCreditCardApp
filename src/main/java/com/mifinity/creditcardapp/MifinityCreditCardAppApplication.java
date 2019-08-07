package com.mifinity.creditcardapp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mifinity.creditcardapp.model.Role;
import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.repository.RoleRepository;
import com.mifinity.creditcardapp.repository.UserRepository;

/**
 * The MifinityCreditCardAppApplication is the main class 
 * which will start the application.
 * 
 * @author Akarsh Jain
 * @version 1.0
 */

@SpringBootApplication
public class MifinityCreditCardAppApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MifinityCreditCardAppApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MifinityCreditCardAppApplication.class);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			Role adminRole = new Role(ApplicationUtil.ROLE_ADMIN);
			roleRepository.save(adminRole);
			roleRepository.save(new Role(ApplicationUtil.ROLE_USER));
			userRepository.save(new User("admin", bCryptPasswordEncoder.encode("password"), adminRole));
		};
	}

}
