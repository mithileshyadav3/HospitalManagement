package com.appointment.dto;

import com.appointment.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public class RegisterRequest {
	@NotNull(message = "username cannot be null")
	@NotBlank(message = "username cannot be empty")
    private String username;
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be empty")
    private String name;
	@Email(message = "email should be valid")
    private String email;
	@Size(
			min = 4,
			max=10,
			message = "password should be between 4 and 10"

	)
    private String password;

    @Enumerated(EnumType.STRING)
	@NotNull(message = "Roles should not be null ")
    private Role role;

    private boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    
}
