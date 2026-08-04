package com.customer.dto;

public class DepartmentResponse {
	  private Long id;
	    private String departmentName;

	    private String description;

	    private String status;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	     
}
