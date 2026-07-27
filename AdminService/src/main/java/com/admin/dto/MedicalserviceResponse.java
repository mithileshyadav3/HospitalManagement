package com.admin.dto;

import com.admin.entity.Department;


public class MedicalserviceResponse {
	 private Long id;

	    private String serviceName;

	    private String description;

	    private Integer duration;

	    private String status;
	    
	    private Department departmentId;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getDuration() {
			return duration;
		}

		public void setDuration(Integer duration) {
			this.duration = duration;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Department getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Department departmentId) {
			this.departmentId = departmentId;
		}
	    
}
