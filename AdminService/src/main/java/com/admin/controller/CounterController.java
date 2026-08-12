package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.CounterRequest;
import com.admin.dto.CounterResponse;
import com.admin.service.CounterService;

@RestController
@RequestMapping("/counter")
public class CounterController {
@Autowired  CounterService counterService;
@PostMapping("/add")
   public ResponseEntity<CounterResponse>addCounter(@RequestBody CounterRequest counterRequest){
	      CounterResponse response= counterService.counterAdd(counterRequest);
	      return ResponseEntity.ok(response);
   }
@GetMapping("/allcounter")
public ResponseEntity<List<CounterResponse>>allCounter(){
	   List<CounterResponse>responses=counterService.counterAll();
	return ResponseEntity.ok(responses);
}
@GetMapping("/onecounter/{id}")
public ResponseEntity<CounterResponse>oneCounter(@PathVariable long id){
	CounterResponse responses =counterService.counterOne(id);
	     return ResponseEntity.ok(responses);
}
@GetMapping("/getcountebystaff/{id}")
public ResponseEntity<CounterResponse>allCounters(@PathVariable Long id){
	   CounterResponse responses1=counterService.counterAllBystaff(id);
	return ResponseEntity.ok(responses1);
}
}
