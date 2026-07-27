package com.appointment.dto;

import com.appointment.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UpdateRequest {
	  private String username;
	    private String name;
	    private String email;

	   

	    @Enumerated(EnumType.STRING)
	    private Role role;

	    private boolean enabled;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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
	    
}
