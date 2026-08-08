package com.admin.clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.admin.dto.RegisterResponse;


@FeignClient(name="AUTHENTICATIONSERVICE")
public interface Clients {
	@GetMapping("users/oneuser/{id}")
	 public RegisterResponse oneUser(@PathVariable Long id);
}
