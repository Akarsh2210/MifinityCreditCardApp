package com.mifinity.creditcardapp.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mifinity.creditcardapp.ApplicationUtil;

/**
 * LoginRedirect class is used to to handle the user request based on the role.
 * @author Akarsh Jain
 * @version 1.0
 */

@Component
public class LoginRedirect implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		 
        if (roles.contains(ApplicationUtil.ROLE_ADMIN)) {
            httpServletResponse.sendRedirect("/searchCard");
        } else {
            httpServletResponse.sendRedirect("/creditCardFunctions");
        }
        
	}

}
