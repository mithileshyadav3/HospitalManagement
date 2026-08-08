package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
