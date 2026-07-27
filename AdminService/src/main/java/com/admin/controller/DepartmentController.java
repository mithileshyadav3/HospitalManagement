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

import com.admin.dto.DepartmentRequest;
import com.admin.dto.DepartmentResponse;
import com.admin.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired DepartmentService departmentService;
	 @PostMapping("/add")
	   public ResponseEntity<DepartmentResponse>addDepart(@RequestBody DepartmentRequest departmentRequest){
		       DepartmentResponse departmentResponse=departmentService.Add(departmentRequest);
		        return ResponseEntity.ok(departmentResponse);
		         
	   }
	 
	 @GetMapping("/alldepartment")
	   public ResponseEntity<List<DepartmentResponse>>allDepart(){
		      List<DepartmentResponse> departmentResponse=departmentService.Alldepart();
		        return ResponseEntity.ok(departmentResponse);
		         
	   }
	 @PutMapping("/update/{id}")
	   public ResponseEntity<DepartmentResponse>updateDepart(@PathVariable Long id,@RequestBody DepartmentRequest departmentRequest){
		       DepartmentResponse departmentResponse=departmentService.update(id,departmentRequest);
		        return ResponseEntity.ok(departmentResponse);
		         
	   }
	 @DeleteMapping("/delete/{id}")
	   public ResponseEntity<String>deleteDepart(@PathVariable Long id){
		       departmentService.departRemove(id);
		        return ResponseEntity.ok("Deleted success");
		         
	   }
	 @GetMapping("/search/{keyword}")
	
	   public ResponseEntity<List<DepartmentResponse>>searchDepart(@PathVariable String keyword){
		      List< DepartmentResponse> departmentResponse=departmentService.departSearch(keyword);
		        return ResponseEntity.ok(departmentResponse);
		         
	   }
}
