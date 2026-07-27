package com.appointment.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appointment.dto.JwtResponse;
import com.appointment.dto.LoginRequest;
import com.appointment.dto.RegisterRequest;
import com.appointment.dto.RegisterResponse;
import com.appointment.dto.UpdateRequest;
import com.appointment.entity.Role;
import com.appointment.entity.User;
import com.appointment.repo.UserRepo;
import com.appointment.tokens_filter.GeneratedToken;
@Service
public class AuthService {
     @Autowired UserRepo userRepo;
     @Autowired ModelMapper mapper;
     @Autowired AuthenticationManager manager;
     @Autowired PasswordEncoder passwordEncoder;
     @Autowired GeneratedToken gtoken;
	public RegisterResponse Registeration(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		     User users=mapper.map(registerRequest, User.class);
		          users.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		     User user=userRepo.save(users) ;   
		  RegisterResponse response=mapper.map(user,RegisterResponse.class);
		  return response;
		
	}
	public JwtResponse GenerateToken(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		  if(authentication.isAuthenticated()) {
			    User  users=  userRepo.findByUsername(loginRequest.getUsername()).get();
			   String token=gtoken.generateToken(users);
			   JwtResponse response=new JwtResponse();
			   response.setRole(users.getRole().name());			     
			   response.setToken(token);
			   return response;
			 		  }
		  
		  throw new RuntimeException("Token is invalid");
	}
	public List<RegisterResponse> RoleBasisUsers(String role) {
		// TODO Auto-generated method stub
		 List<User>  user=userRepo.findByRole(Role.valueOf(role.toUpperCase()));
		  return user.stream()
				  .map(users->mapper.map(users, RegisterResponse.class))
				  .toList();
		 
	}
	public RegisterResponse updatingUsers(Long id,UpdateRequest updateRequest) {
		// TODO Auto-generated method stub
	               User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("id not found"));
	           mapper.map(updateRequest,user);
	            userRepo.save(user);
	           return mapper.map(user,RegisterResponse.class);
	}
	public RegisterResponse DeletUser(Long id) {
		// TODO Auto-generated method stub
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("id not found"));
         userRepo.deleteById(id);
         userRepo.save(user);

		return mapper.map(user,RegisterResponse.class);
	}
	public List<RegisterResponse> searchingName(String keyword) {
		// TODO Auto-generated method stub
		   List<User>nameList=userRepo.findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(keyword,keyword);
		    if(!nameList.isEmpty()) {
		    	return nameList.stream()
		    			.map(names->mapper.map(names,RegisterResponse.class))
		    			.toList();
		    }
		throw new RuntimeException("This Name isn't present in list");
	}

}
