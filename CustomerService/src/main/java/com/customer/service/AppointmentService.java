package com.customer.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.clients.AdminClients;

import com.customer.dto.AppointmentRequest;
import com.customer.dto.AppointmentResponse;
import com.customer.dto.DepartmentResponse;
import com.customer.dto.MedicalServiceResponse;
import com.customer.entity.Appointment;
import com.customer.repo.PatientRepo;
@Service
public class AppointmentService {
        @Autowired AdminClients adminClients;
       @Autowired PatientRepo appointmentRepo;
       @Autowired ModelMapper mapper;
	public AppointmentResponse appointmentAdd(AppointmentRequest appointmentRequest) {
		// TODO Auto-generated method stub
		                 
		      DepartmentResponse departmentResponse=adminClients.oneDepartment(appointmentRequest.getDepartmentId());
		         if(departmentResponse==null) {
		        	 throw new RuntimeException("Department id doesn't exists");
		         }
		      
		      MedicalServiceResponse medicalServiceResponse=adminClients.oneMedical(appointmentRequest.getServiceId());
		        if(medicalServiceResponse==null) {
		        	 throw new RuntimeException("Medical service  id doesn't exists");
		        }
		        
		        if (!medicalServiceResponse.getDepartmentId().equals(departmentResponse.getId())) {
		            throw new RuntimeException("Medical Service does not belong to the selected Department.");
		        }
		        Appointment appointment = new Appointment();

		        
		        appointment.setDepartmentId(appointmentRequest.getDepartmentId());
		        appointment.setServiceId(appointmentRequest.getServiceId());
		        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
		        appointment.setRemarks(appointmentRequest.getRemarks());
		        String token=generateToken(appointmentRequest.getAppointmentDate());
		        appointment.setTokenNumber(token);
		        appointment.setStatus("WAITING");
		        appointment.setPatientname(appointmentRequest.getPatientname()); //note patientname instead of patientid in table but in real patients name
		   Appointment appointment2=    appointmentRepo.save(appointment);
		 return  mapper.map(appointment2,AppointmentResponse.class);		      
	}
   public String generateToken(LocalDate date) {
	        long count=appointmentRepo.countByAppointmentDate(date);
	   return "A"+String.format("%03d", count+1);
   }
   public AppointmentResponse appointmentUpdate(Long id, AppointmentRequest updaterequest) {
	// TODO Auto-generated method stub
	    DepartmentResponse departmentResponse=adminClients.oneDepartment(updaterequest.getDepartmentId());
        if(departmentResponse==null) {
       	 throw new RuntimeException("Department id doesn't exists");
        }
     
     MedicalServiceResponse medicalServiceResponse=adminClients.oneMedical(updaterequest.getServiceId());
       if(medicalServiceResponse==null) {
       	 throw new RuntimeException("Medical service  id doesn't exists");
       }
	              Appointment appointment=appointmentRepo.findById(id).orElseThrow(()->new RuntimeException("appointment id doesn't exists"));
	             appointment.setAppointmentDate(updaterequest.getAppointmentDate());  
	             appointment.setPatientname(updaterequest.getPatientname());
	             appointment.setDepartmentId(updaterequest.getDepartmentId());
	             appointment.setRemarks(updaterequest.getRemarks());
	             appointment.setServiceId(updaterequest.getServiceId());
	             appointmentRepo.save(appointment);
	return mapper.map(appointment,AppointmentResponse.class);
   }
   public List<AppointmentResponse> allAppointment() {
	// TODO Auto-generated method stub
	    List<Appointment>appointments=appointmentRepo.findAll();
	    
	return appointments.stream()
			.map(appointed->mapper.map(appointed, AppointmentResponse.class))
			.toList();
   }
   public void deleteAppointment(Long id) {
	// TODO Auto-generated method stub
	                if(!appointmentRepo.existsById(id)) {
	                	throw new RuntimeException("appointment id doesn't exits");
	                }
	            appointmentRepo.deleteById(id);    
	                
   }
}
