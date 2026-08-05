package com.customer.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.entity.Appointment;


@Repository
public interface PatientRepo extends JpaRepository<Appointment,Long> {
   long countByAppointmentDate(LocalDate date);
   List<Appointment>findByPatientnameContainingIgnoreCase(String name);
}
