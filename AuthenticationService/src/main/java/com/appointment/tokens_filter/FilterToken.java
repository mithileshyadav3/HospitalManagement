package com.appointment.tokens_filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.appointment.utility.CustomUseDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FilterToken extends OncePerRequestFilter {
   @Autowired GeneratedToken generatedToken;
   @Autowired CustomUseDetailsService customUseDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		      String authHeader= request.getHeader("Authorization");
		      String username=null;
		      String token=null;
		      try {
		      if(authHeader!=null && authHeader.startsWith("Bearer ")) {
		    	  token=authHeader.substring(7);
		    	  username=generatedToken.getUsername(token);
		      }
		       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		    	UserDetails userDetails= customUseDetailsService.loadUserByUsername(username);
		    	if(generatedToken.isToken(token, userDetails)) {
		    		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
		    		authenticationToken.setDetails( new WebAuthenticationDetailsSource()
                    .buildDetails(request));
		    		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		    	}
		       }
filterChain.doFilter(request, response);
		      }
		      catch(Exception e) {
		    	  new RuntimeException("token is not valid");
		      }
		
	}

}
