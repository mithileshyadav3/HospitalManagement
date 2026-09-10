package com.customer.service;

import com.customer.clients.AdminClients;
import com.customer.dto.DepartmentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class AdminClientService {
    private final AdminClients adminClients;

    public AdminClientService(AdminClients adminClients) {
        this.adminClients = adminClients;
    }

    @CircuitBreaker(
            name = "departmentService",
            fallbackMethod = "departmentFallback"
    )
    public DepartmentResponse getDepartment(Long departmentId) {

        System.out.println(">>> getDepartment() CALLED");

        return adminClients.oneDepartment(departmentId);
    }

    public DepartmentResponse departmentFallback(
            Long departmentId,
            Exception ex) {

        System.out.println(">>> FALLBACK CALLED");
        System.out.println("Reason: " + ex.getMessage());

        DepartmentResponse response = new DepartmentResponse();
        response.setDepartmentName("Department unavailable");

        return response;
    }
}
