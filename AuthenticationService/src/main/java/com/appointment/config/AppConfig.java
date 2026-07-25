package com.appointment.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appointment.tokens_filter.FilterToken;

@Configuration
@EnableWebSecurity
public class AppConfig {
	@Autowired FilterToken filterToken;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 return  http.csrf(c->c.disable())
	       .authorizeHttpRequests(req->req
	    	.requestMatchers("/users/**").permitAll()
            .anyRequest()
	    	.authenticated()
	    	) 
	       .httpBasic(Customizer.withDefaults())
           .formLogin(Customizer.withDefaults())
           .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
	       .build();
}
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}
@Bean 
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
	return configuration.getAuthenticationManager();
}
}
