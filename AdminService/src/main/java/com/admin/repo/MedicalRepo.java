package com.admin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.MedicalService;

public interface MedicalRepo extends JpaRepository<MedicalService, Long> {
         List<MedicalService>findByServiceName(String keyword);
}
