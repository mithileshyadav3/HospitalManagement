package com.customer.dto;

import java.time.LocalDate;

public class AppointmentResponse {
	 private Long appointmentId;
	    private String tokenNumber;
	    private String status;
	    private LocalDate appointmentDate;
	    private String patientName; 
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
	    
}
