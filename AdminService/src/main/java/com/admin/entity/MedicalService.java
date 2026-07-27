package com.admin.entity;

import jakarta.persistence.*;

@Entity
@Table(name="hospital_services")
public class MedicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Integer duration;

    private String status;
     @ManyToOne
     @JoinColumn(name="department_id")
    private Long departmentId;
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
	 public Long getDepartmentId() {
		 return departmentId;
	 }
	 public void setDepartmentId(Long departmentId) {
		 this.departmentId = departmentId;
	 }
     
    
}