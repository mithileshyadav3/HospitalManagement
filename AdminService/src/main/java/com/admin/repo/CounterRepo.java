package com.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Counter;
@Repository
public interface CounterRepo extends JpaRepository<Counter, Long> {

}
