package com.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.dto.JwtResponse;
import com.appointment.dto.LoginRequest;
import com.appointment.dto.RegisterRequest;
import com.appointment.dto.RegisterResponse;
import com.appointment.dto.UpdateRequest;
import com.appointment.service.AuthService;

@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired AuthService authService;
	   @PostMapping("/register")
	   public ResponseEntity<RegisterResponse>Register(@RequestBody RegisterRequest registerRequest){
		       RegisterResponse registerResponse=authService.Registeration(registerRequest);
		        return ResponseEntity.ok(registerResponse);
		         
	   }
	   @PostMapping("/login")
	   public ResponseEntity<JwtResponse> Login(@RequestBody LoginRequest loginRequest)
	   {
		     JwtResponse jwtResponse=authService.GenerateToken(loginRequest);
		                  return ResponseEntity.ok(jwtResponse);
	   }
	   @GetMapping("/roles/{role}")
	     public ResponseEntity<List<RegisterResponse>>fetchDataOnBasisofRoles(@PathVariable String role)
	     {
		      List<RegisterResponse> registerResponse=authService.RoleBasisUsers(role);
		   return ResponseEntity.ok(registerResponse);
	   }
	   @PutMapping("/update/{id}")
	   public ResponseEntity<RegisterResponse>updateUsers(@PathVariable Long id,  @RequestBody UpdateRequest updateRequest)
	   {
	       RegisterResponse registerResponse=authService.updatingUsers(id,updateRequest);
	        return ResponseEntity.status(HttpStatus.OK).body(registerResponse);
	         
	   }
	   @DeleteMapping("/delete/{id}")
	   public ResponseEntity<RegisterResponse>DeleteUsersById(@PathVariable Long id){
	       RegisterResponse registerResponse=authService.DeletUser(id);
	        return ResponseEntity.ok(registerResponse);
	   }
	   @GetMapping("/search/{name}")
	   public ResponseEntity<List<RegisterResponse>>SearchByName(@PathVariable String name){
	       List<RegisterResponse> registerResponse=authService.searchingName(name);
	        return ResponseEntity.ok(registerResponse);
	   }
	   
}
	         
   