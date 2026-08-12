package com.customer.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.entity.Appointment;


@Repository
public interface PatientRepo extends JpaRepository<Appointment,Long> {
   
   List<Appointment>findByPatientnameContainingIgnoreCase (String name);
     long  countByAppointmentDate(LocalDate date);
      long countByAppointmentDateAndStatus(LocalDate date,String status);
//      long countByStatus(String status);
      Optional<Appointment>findFirstByAppointmentDateAndStatusOrderByIdAsc(LocalDate dateorder,String status);
      List<Appointment>findByDepartmentId(long id);
}
