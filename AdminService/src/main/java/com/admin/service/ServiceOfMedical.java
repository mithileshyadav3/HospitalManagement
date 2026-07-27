package com.admin.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.MedicalserviceRequest;
import com.admin.dto.MedicalserviceResponse;
import com.admin.entity.MedicalService;
import com.admin.repo.MedicalRepo;

@Service
public class ServiceOfMedical {
   @Autowired ModelMapper mapper;
   @Autowired MedicalRepo medicalRepo;
	public MedicalserviceResponse Add(MedicalserviceRequest medicalserviceRequest) {
		// TODO Auto-generated method stub
		          MedicalService medicalService= mapper.map(medicalserviceRequest, MedicalService.class);
		      MedicalService medicalService2=     medicalRepo.save(medicalService);
		return mapper.map(medicalService2, MedicalserviceResponse.class);
	}

	public List<MedicalserviceResponse> allMedical() {
		// TODO Auto-generated method stub
		List<MedicalService> medicalService= medicalRepo.findAll();    
		return medicalService.stream()
				.map(service->mapper.map(service, MedicalserviceResponse.class))
				.toList();
	}

	public MedicalserviceResponse medicalUpdate(Long id, MedicalserviceRequest medicalserviceRequest) {
		// TODO Auto-generated method stub
		    MedicalService medicalService=medicalRepo.findById(id).orElseThrow(()->new RuntimeException("medical service id doesn't exit"));
		              mapper.map(medicalserviceRequest,medicalService ) ;
		              medicalRepo.save(medicalService);
		return mapper.map(medicalService, MedicalserviceResponse.class);
	}

	public void medicalDelete(Long id) {
		// TODO Auto-generated method stub
		 medicalRepo.findById(id).orElseThrow(()->new RuntimeException("medical service id doesn't exit"));
		 medicalRepo.deleteById(id);
	}

	public List<MedicalserviceResponse> searchMedical(String keyword) {
		// TODO Auto-generated method stub
		List<MedicalService> medicalService=medicalRepo.findByServiceName(keyword);
		return medicalService.stream()
				.map(service->mapper.map(service, MedicalserviceResponse.class))
				.toList();
		
	}

}
