package com.customer.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.dto.AppointmentRequest;
import com.customer.dto.AppointmentResponse;
import com.customer.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired AppointmentService appointmentService;
        @PostMapping("/add")
        public ResponseEntity<AppointmentResponse>addAppointment(@RequestBody AppointmentRequest appointmentRequest){
        AppointmentResponse response= appointmentService.appointmentAdd(appointmentRequest);
        	 return ResponseEntity.ok(response);
        }
        
      @GetMapping("/allappointment")
       public ResponseEntity<List<AppointmentResponse>>getallAppointment(){
    	  List<AppointmentResponse>responses=appointmentService.allAppointment();
    	  return ResponseEntity.ok(responses);
      }
        @PutMapping("/update/{id}")
        public ResponseEntity<AppointmentResponse>updateAppointment(@PathVariable Long id,@RequestBody AppointmentRequest updaterequest){
        	   AppointmentResponse response=appointmentService.appointmentUpdate(id,updaterequest);
        	    return ResponseEntity.ok(response);
        }
        
        @DeleteMapping("/delete/{id}")
        public String appointmentDelete(@PathVariable Long id) {
        	          appointmentService.deleteAppointment(id);
        	       return   "Deleted Appointment Success";
        }
        @GetMapping("/search/{name}")
        public ResponseEntity<List<AppointmentResponse>>searchallAppointment(@PathVariable String name){
      	  List<AppointmentResponse>responses=appointmentService.Appointmentsearch(name);
      	  return ResponseEntity.ok(responses);
        }
        @PutMapping("/cancel/{id}")
        public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable Long id) {

            return ResponseEntity.ok(appointmentService.cancelAppointment(id));
        }
        
}
