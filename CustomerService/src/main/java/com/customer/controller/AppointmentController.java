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
import com.customer.dto.AppointmentStatusRequest;
import com.customer.dto.QueueDashboardResponse;
import com.customer.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
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
        @PutMapping("/cancel/{id}/{status}")
        public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable Long id,@PathVariable String status) {

            return ResponseEntity.ok(appointmentService.cancelAppointment(id,status));
        }
        //for the change of status
        @PutMapping("/changestatus/{id}")
        public ResponseEntity<AppointmentResponse>updateStatus(@PathVariable Long id,@RequestBody AppointmentStatusRequest statusRequest){
        	         AppointmentResponse response=appointmentService.statusUpdate(id,statusRequest);
        	         return  ResponseEntity.ok(response);      
        	        		 
        }
        @GetMapping("/queue-dashboard")
        public ResponseEntity<QueueDashboardResponse>calcutAppointment(){
        	return ResponseEntity.ok(appointmentService.calAppointment());
        }
        @GetMapping("/call-next")
        public ResponseEntity<AppointmentResponse> callNext() {

            AppointmentResponse response =
                    appointmentService.callNext();

            return ResponseEntity.ok(response);
        }
        @GetMapping("/bydepartment/{staffid}")
        public ResponseEntity<List<AppointmentResponse>>searchBydeparmentId(@PathVariable long staffid){
        	  List<AppointmentResponse>responses=appointmentService.DepartmentIdSearch(staffid);
        	  return ResponseEntity.ok(responses);
          }

}
