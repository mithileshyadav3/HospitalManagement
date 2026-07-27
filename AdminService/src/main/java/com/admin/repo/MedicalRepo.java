package com.admin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.MedicalService;
@Repository
public interface MedicalRepo extends JpaRepository<MedicalService, Long> {
	List<MedicalService> findByServiceNameContainingIgnoreCase(String keyword);
}
