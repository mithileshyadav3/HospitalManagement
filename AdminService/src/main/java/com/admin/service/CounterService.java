package com.admin.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.clients.Clients;
import com.admin.dto.CounterRequest;
import com.admin.dto.CounterResponse;
import com.admin.dto.RegisterResponse;
import com.admin.entity.Counter;
import com.admin.entity.Department;
import com.admin.repo.CounterRepo;
import com.admin.repo.DepartmentRepo;

@Service
public class CounterService {
   @Autowired CounterRepo counterRepo;
   @Autowired DepartmentRepo departmentRepo;
   @Autowired Clients clients; 
   @Autowired ModelMapper mapper;
   public CounterResponse counterAdd(CounterRequest counterRequest) {
	// TODO Auto-generated method stub
	       
	       Department department=departmentRepo.findById(counterRequest.getDepartmentId()).orElseThrow(()->
	       new RuntimeException("department id doesn't exists"));
	           RegisterResponse registerResponse= clients.oneUser(counterRequest.getStaffId());
	           if(registerResponse==null) {
	        	   throw new RuntimeException("staff or user id doesn't exists");
	           }
	        // Check whether staff is already assigned
	           Optional<Counter> existingCounter =
	                   counterRepo.findByStaffId(counterRequest.getStaffId());

	           if (existingCounter.isPresent()) {

	               Counter oldCounter = existingCounter.get();

	               throw new RuntimeException(
	                   "Staff is already assigned to Department ID "
	                   + oldCounter.getDepartmentId()
	               );
	           }
	          Counter counter=new Counter();
	           counter.setCounterName(counterRequest.getCounterName());
	           counter.setDepartmentId(department.getId());
	           counter.setStaffId(registerResponse.getId());
	           counter.setStatus(counterRequest.getStatus());
	              counterRepo.save(counter);
	CounterResponse counterResponse= mapper.map(counter, CounterResponse.class);
	    counterResponse.setDepartmentName(department.getDepartmentName());
	    counterResponse.setStaffName(registerResponse.getName());
	    return counterResponse;
   }
   public List<CounterResponse> counterAll() {
	       // TODO Auto-generated method stub
	       List<Counter>counterResponses=counterRepo.findAll();
	        List<CounterResponse>responses=new ArrayList<>();
	       for(Counter counter:counterResponses) {
	    	   Department department=departmentRepo.findById(counter.getDepartmentId()).orElseThrow(()->
		       new RuntimeException("department id doesn't exists"));
		           RegisterResponse registerResponse= clients.oneUser(counter.getStaffId());
		           if(registerResponse==null) {
		        	   throw new RuntimeException("staff or user id doesn't exists");
		           }
		           CounterResponse res=mapper.map(counter,CounterResponse.class);
		           res.setDepartmentName(department.getDepartmentName());
		           res.setStaffName(registerResponse.getName());
		           responses.add(res);;
	       }
	       return responses;
	        
   }
   public CounterResponse counterOne(long id) {
	// TODO Auto-generated method stub
	      Counter counter= counterRepo.findById(id).orElseThrow(()->new RuntimeException("counter id doesn't exists"));
	         
	      return mapper.map(counter, CounterResponse.class);
   }
   public CounterResponse counterAllBystaff(Long id) {
	// TODO Auto-generated method stub
	    Counter responseCounters=counterRepo.findByStaffId(id).orElseThrow(()->new RuntimeException("staff id doesn't exists"));
	    
	    return mapper.map(responseCounters, CounterResponse.class);
   }
}
