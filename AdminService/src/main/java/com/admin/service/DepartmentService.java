package com.admin.service;

import java.util.List;

import com.admin.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.DepartmentRequest;
import com.admin.dto.DepartmentResponse;
import com.admin.entity.Department;
import com.admin.repo.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired DepartmentRepo departmentRepo;
    @Autowired ModelMapper mapper;
	public DepartmentResponse Add(DepartmentRequest departmentRequest) {
	            Department department= mapper.map(departmentRequest,Department.class);   
	            Department department2=departmentRepo.save(department);
	             return  mapper.map(department2, DepartmentResponse.class);
		
	}

	public List<DepartmentResponse> Alldepart() {
		// TODO Auto-generated method stub
		  List< Department> department2=departmentRepo.findAll(); 
		    return department2.stream()
		    		.map(department->mapper.map(department,DepartmentResponse.class))
		    		.toList();
			}

	public DepartmentResponse update(Long id, DepartmentRequest departmentRequest) {
		// TODO Auto-generated method stub
		        Department department=      departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department id doesn't exists"));
		           mapper.map(departmentRequest, department);
		           departmentRepo.save(department);
		        return   mapper.map(department,DepartmentResponse.class);
		          
		
	}

	public void departRemove(Long id) {
		// TODO Auto-generated method stub
		departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department id doesn't exists"));
		        departmentRepo.deleteById(id);
	}

	public List<DepartmentResponse> departSearch(String keyword) {
		// TODO Auto-generated method stub
		   List<Department>departments= departmentRepo.findByDepartmentNameContainingIgnoreCase(keyword);
		   
		return departments.stream()
				.map(depart->mapper.map(depart, DepartmentResponse.class))
				.toList();
	}

	public DepartmentResponse departmentOne(Long id) {
		// TODO Auto-generated method stub
		  Department department=departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department id not found"));
		return mapper.map(department, DepartmentResponse.class);
	}

}
