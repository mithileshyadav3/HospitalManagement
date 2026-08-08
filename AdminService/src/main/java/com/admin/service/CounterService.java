package com.admin.service;

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
	          Counter counter=new Counter();
	           counter.setCounterName(counterRequest.getCounterName());
	           counter.setDepartmentId(department.getId());
	           counter.setStaffId(registerResponse.getId());
	           counter.setStatus(counterRequest.getStatus());
	              counterRepo.save(counter);
	return mapper.map(counter, CounterResponse.class);
   }
}
