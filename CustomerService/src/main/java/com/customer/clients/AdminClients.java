package com.customer.clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.CounterResponse;
import com.customer.dto.DepartmentResponse;
import com.customer.dto.MedicalServiceResponse;

@FeignClient(name = "ADMINSERVICE")
public interface AdminClients {

    @GetMapping("/department/onedepartment/{id}")
    DepartmentResponse oneDepartment(@PathVariable Long id);

    @GetMapping("/medical/onemedical/{id}")
    MedicalServiceResponse oneMedical(@PathVariable Long id);
    
    @GetMapping("/counter/onecounter/{id}")
    public CounterResponse oneCounter(@PathVariable long id);
}