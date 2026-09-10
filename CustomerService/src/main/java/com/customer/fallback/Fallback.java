//package com.customer.fallback;
//
//import com.customer.clients.AdminClients;
//import com.customer.dto.CounterResponse;
//import com.customer.dto.DepartmentResponse;
//import com.customer.dto.MedicalServiceResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Fallback  {
//
//    @Override
//    public DepartmentResponse oneDepartment(Long id) {
//        System.out.println("fallback executed");
//        return null;
//    }
//
//    @Override
//    public MedicalServiceResponse oneMedical(Long id) {
//        return null;
//    }
//
//    @Override
//    public CounterResponse oneCounter(long id) {
//        return null;
//    }
//}
