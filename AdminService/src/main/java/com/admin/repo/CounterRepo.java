package com.admin.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Counter;
@Repository
public interface CounterRepo extends JpaRepository<Counter, Long> {
          long countById(long id);
          Optional<Counter>findByStaffId(long id);
}
