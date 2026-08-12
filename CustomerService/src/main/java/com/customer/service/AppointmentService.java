package com.customer.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.clients.AdminClients;

import com.customer.dto.AppointmentRequest;
import com.customer.dto.AppointmentResponse;
import com.customer.dto.AppointmentStatusRequest;
import com.customer.dto.CounterResponse;
import com.customer.dto.DepartmentResponse;
import com.customer.dto.MedicalServiceResponse;
import com.customer.dto.QueueDashboardResponse;
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
		        appointment.setAddress(appointmentRequest.getAddress());
		        appointment.setSex(appointmentRequest.getSex());
		        appointment.setAge(appointmentRequest.getAge());
		        String token=generateToken(appointmentRequest.getAppointmentDate());
		        appointment.setTokenNumber(token);
		        appointment.setStatus("WAITING");
		        appointment.setPatientname(appointmentRequest.getPatientname()); //note patientname instead of patientid in table but in real patients name
		   Appointment appointment2=    appointmentRepo.save(appointment);
		 AppointmentResponse appointmentResponse=  mapper.map(appointment2,AppointmentResponse.class);	
		       appointmentResponse.setDepartname(departmentResponse.getDepartmentName());
		       appointmentResponse.setMedicalservicename(medicalServiceResponse.getServiceName());
		      return appointmentResponse;
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

	    List<Appointment> appointments = appointmentRepo.findAll();

	    List<AppointmentResponse> responses = new ArrayList<>();

	    for (Appointment appointment : appointments) {

	        // Get Department using Feign
	        DepartmentResponse department =
	                adminClients.oneDepartment(appointment.getDepartmentId());

	        // Get Medical Service using Feign
	        MedicalServiceResponse medical =
	                adminClients.oneMedical(appointment.getServiceId());

	        // Map common fields
	        AppointmentResponse response =
	                mapper.map(appointment, AppointmentResponse.class);

	        // Set extra fields
	        response.setDepartname(department.getDepartmentName());
	        response.setMedicalservicename(medical.getServiceName());

	        responses.add(response);
	    }

	    return responses;
	}
   public void deleteAppointment(Long id) {
	// TODO Auto-generated method stub
	                if(!appointmentRepo.existsById(id)) {
	                	throw new RuntimeException("appointment id doesn't exits");
	                }
	            appointmentRepo.deleteById(id);    
	                
   }
   public List<AppointmentResponse> Appointmentsearch(String name) {
	// TODO Auto-generated method stub
	          List<Appointment>appointments=appointmentRepo.findByPatientnameContainingIgnoreCase(name);
	          if(appointments==null) {
	        	  throw new RuntimeException("No Search Found");
	          }
	return  appointments.stream()
			.map(app->mapper.map(app, AppointmentResponse.class))
			.toList();
   }
   public AppointmentResponse cancelAppointment(Long id) {
	// TODO Auto-generated method stub
	          Appointment appointment=appointmentRepo.findById(id).orElseThrow(()->new RuntimeException("appointment id doesn't exists"));
	           appointment.setStatus("CANCELLED");
	     Appointment updateAppointment=appointmentRepo.save(appointment);
	return mapper.map(updateAppointment, AppointmentResponse.class);
   }
   public AppointmentResponse statusUpdate(Long id, AppointmentStatusRequest statusRequest) {
	// TODO Auto-generated method stub
	   Appointment appointment=appointmentRepo.findById(id).orElseThrow(()->new RuntimeException("appointment id doesn't exists"));
       appointment.setStatus(statusRequest.getStatus());
	  Appointment updateAppointment=appointmentRepo.save(appointment);
	return mapper.map(updateAppointment, AppointmentResponse.class);
   }
   public QueueDashboardResponse calAppointment() {
	// TODO Auto-generated method stub
	   LocalDate todayDate= LocalDate.now();
	   QueueDashboardResponse response=new QueueDashboardResponse();
	     long totalappointments=appointmentRepo.countByAppointmentDate(todayDate);
	     long waiting=appointmentRepo.countByAppointmentDateAndStatus(todayDate,"WAITING");
	     long inprogress=appointmentRepo.countByAppointmentDateAndStatus(todayDate,"IN_PROGRESS");
	     long complete=appointmentRepo.countByAppointmentDateAndStatus(todayDate,"DONE");
	     long cancelled=appointmentRepo.countByAppointmentDateAndStatus(todayDate,"CANCELLED");
	     response.setCancelled(cancelled);
	     response.setCompleted(complete);
	     response.setInProgress(inprogress);
	     response.setTotalAppointments(totalappointments);
	     response.setWaiting(waiting);
	    
	return response;
   }
   public AppointmentResponse callNext() {
	// TODO Auto-generated method stub
	   LocalDate today=LocalDate.now();
	     Optional<Appointment>nextappointment=   
	    		 appointmentRepo.findFirstByAppointmentDateAndStatusOrderByIdAsc(today, "WAITING");
	     if(nextappointment.isEmpty()) {
	    	 throw new RuntimeException("No waiting patient ");
	     }
	    Appointment appointment=   nextappointment.get();
	     appointment.setStatus("IN_PROGRESS");
	         Appointment appointment2=   appointmentRepo.save(appointment);
	return mapper.map(appointment2, AppointmentResponse.class);
   }
   
   public List<AppointmentResponse> DepartmentIdSearch(long id) {
	// TODO Auto-generated method stub
//	         CounterResponse counterResponse=adminClients.oneCounter(id);
//	         if(counterResponse==null) {
//	        	 throw new RuntimeException("Counter id doesn't exists");
//	         }
	   List<Appointment>appointments=appointmentRepo.findByDepartmentId(id);
       if(appointments==null) {
     	  throw new RuntimeException("No Deparment Assign to the Counter");
       }
   
return  appointments.stream()
		.map(app->mapper.map(app, AppointmentResponse.class))
		.toList();
   }
}
