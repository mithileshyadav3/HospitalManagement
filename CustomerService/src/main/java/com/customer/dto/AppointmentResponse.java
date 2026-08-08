package com.customer.dto;

import java.time.LocalDate;

public class AppointmentResponse {
	 private Long appointmentId;
	    private String tokenNumber;
	    private String status;
	    private LocalDate appointmentDate;
	    private String patientName; 
	    private String remarks;
	    private String departname;
	    private String medicalservicename;
	    private Long age;
	    private String sex;
	    private String address;
		public Long getAppointmentId() {
			return appointmentId;
		}
		public void setAppointmentId(Long appointmentId) {
			this.appointmentId = appointmentId;
		}
		public String getTokenNumber() {
			return tokenNumber;
		}
		public void setTokenNumber(String tokenNumber) {
			this.tokenNumber = tokenNumber;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDate getAppointmentDate() {
			return appointmentDate;
		}
		public void setAppointmentDate(LocalDate appointmentDate) {
			this.appointmentDate = appointmentDate;
		}
		
		
		public String getPatientName() {
			return patientName;
		}
		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public Long getAge() {
			return age;
		}
		public void setAge(Long age) {
			this.age = age;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getDepartname() {
			return departname;
		}
		public void setDepartname(String departname) {
			this.departname = departname;
		}
		public String getMedicalservicename() {
			return medicalservicename;
		}
		public void setMedicalservicename(String medicalservicename) {
			this.medicalservicename = medicalservicename;
		}
		
	    
}
