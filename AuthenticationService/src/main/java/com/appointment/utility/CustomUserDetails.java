package com.appointment.utility;


import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.appointment.entity.User;


public class CustomUserDetails implements UserDetails {
	       private User users;
          public CustomUserDetails(User user) {
        	  this.users=user;
          }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
	      return List.of(new SimpleGrantedAuthority("ROLE_"+users.getRole().name()));
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getUsername();
	}

}
