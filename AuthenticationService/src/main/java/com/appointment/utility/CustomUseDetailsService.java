package com.appointment.utility;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.appointment.entity.User;
import com.appointment.repo.UserRepo;
@Component
public class CustomUseDetailsService implements UserDetailsService{
	  private UserRepo userRepo;
    public CustomUseDetailsService(UserRepo userRepo) {
    	this.userRepo=userRepo;
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		      User  users=userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User Not found"));
		      
		return new CustomUserDetails(users);
	}

}
