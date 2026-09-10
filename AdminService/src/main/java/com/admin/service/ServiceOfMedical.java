package com.admin.service;

import java.util.List;

import com.admin.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.MedicalserviceRequest;
import com.admin.dto.MedicalserviceResponse;
import com.admin.entity.Department;
import com.admin.entity.MedicalService;
import com.admin.repo.DepartmentRepo;
import com.admin.repo.MedicalRepo;

@Service
public class ServiceOfMedical {
   @Autowired ModelMapper mapper;
   @Autowired DepartmentRepo departmentRepo;
   @Autowired MedicalRepo medicalRepo;
	public MedicalserviceResponse Add(MedicalserviceRequest request) {
		// TODO Auto-generated method stub
		 System.out.println("Department Id = " + request.getDepartmentId());
		Department department = departmentRepo.findById(request.getDepartmentId())
		        .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		MedicalService medicalService = new MedicalService();

		medicalService.setServiceName(request.getServiceName());
		medicalService.setDescription(request.getDescription());
		medicalService.setDuration(request.getDuration());
		medicalService.setStatus(request.getStatus());

		medicalService.setDepartment(department);

		medicalRepo.save(medicalService);
		return mapper.map(medicalService,MedicalserviceResponse.class);
	}

	public List<MedicalserviceResponse> allMedical() {
		// TODO Auto-generated method stub
		List<MedicalService> medicalService= medicalRepo.findAll();    
		return medicalService.stream()
				.map(service->mapper.map(service, MedicalserviceResponse.class))
				.toList();
	}

	public MedicalserviceResponse medicalUpdate(Long id, MedicalserviceRequest request) {

	    MedicalService medicalService = medicalRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Medical Service id not found"));

	    // Map only simple fields
	    medicalService.setServiceName(request.getServiceName());
	    medicalService.setDescription(request.getDescription());
	    medicalService.setDuration(request.getDuration());
	    medicalService.setStatus(request.getStatus());

	    // Fetch Department from DB
	    Department department = departmentRepo.findById(request.getDepartmentId())
	            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

	    // Set new Department
	    medicalService.setDepartment(department);

	    medicalRepo.save(medicalService);

	    return mapper.map(medicalService, MedicalserviceResponse.class);
	}
	public void medicalDelete(Long id) {
		// TODO Auto-generated method stub
		 medicalRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("medical service id doesn't exit"));
		 medicalRepo.deleteById(id);
	}

	public List<MedicalserviceResponse> searchMedical(String keyword) {
		// TODO Auto-generated method stub
		List<MedicalService> medicalService=medicalRepo.findByServiceNameContainingIgnoreCase(keyword);
		return medicalService.stream()
				.map(service->mapper.map(service, MedicalserviceResponse.class))
				.toList();
		
	}

	public MedicalserviceResponse medicalOne(Long id) {
		// TODO Auto-generated method stub
		 // Fetch Department from DB
		 MedicalService response= medicalRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("medical service id doesn't exit"));

		return mapper.map(response, MedicalserviceResponse.class);
	}

	public List<MedicalserviceResponse> medicalwithDepartment(Long id) {
		// TODO Auto-generated method stub
		        List<MedicalService>medicalServices=medicalRepo.findByDepartmentId(id);
		        return medicalServices.stream()
		        		.map(mdservice->mapper.map(mdservice, MedicalserviceResponse.class))
		        		.toList();
	}

}
