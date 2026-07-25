package com.appointment.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
     @Autowired CustomUseDetailsService customUseDetailsService;
     @Autowired PasswordEncoder passwordEncoder;
	@Override
	public  Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		   String username=authentication.getName();
		      UserDetails userDetails=customUseDetailsService.loadUserByUsername(username);
		       if(passwordEncoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())) {
		    	 return   new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
		       }
		throw new BadCredentialsException("Password is invalid");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
