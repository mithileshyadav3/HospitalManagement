package com.admin.controller;

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

import com.admin.dto.MedicalserviceRequest;
import com.admin.dto.MedicalserviceResponse;
import com.admin.service.ServiceOfMedical;

@RestController
@RequestMapping("/medical")
public class MedicalserviceController {
	@Autowired ServiceOfMedical serviceOfMedical;
	 @PostMapping("/add")
	   public ResponseEntity<MedicalserviceResponse>addMedical(@RequestBody MedicalserviceRequest medicalserviceRequest){
		       MedicalserviceResponse medicalresponse=serviceOfMedical.Add(medicalserviceRequest);
		        return ResponseEntity.ok(medicalresponse);
		         
	   }
	 @GetMapping("/allmedical")
	   public ResponseEntity<List<MedicalserviceResponse>>medicalAll(){
		      List< MedicalserviceResponse> medicalresponse=serviceOfMedical.allMedical();
		        return ResponseEntity.ok(medicalresponse);
		         
	   }
	 @PutMapping("/update/{id}")
	   public ResponseEntity<MedicalserviceResponse>updateMedical(@PathVariable Long id,@RequestBody MedicalserviceRequest medicalserviceRequest){
		       MedicalserviceResponse medicalresponse=serviceOfMedical.medicalUpdate(id,medicalserviceRequest);
		        return ResponseEntity.ok(medicalresponse);
		         
	   }
	 @DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteDepart(@PathVariable Long id){
		      serviceOfMedical.medicalDelete(id);
		        return ResponseEntity.ok("Delete success");
		         }
	 
	 @GetMapping("/search/{keyword}")

	   public ResponseEntity<List<MedicalserviceResponse>>medicalSearch(@PathVariable String keyword){
		      List< MedicalserviceResponse> medicalresponse=serviceOfMedical.searchMedical(keyword);
		        return ResponseEntity.ok(medicalresponse);
		         
	   }
}
