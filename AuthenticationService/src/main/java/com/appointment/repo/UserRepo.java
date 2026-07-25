package com.appointment.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.Role;
import com.appointment.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
      Optional<User>findByUsername(String username);
      List<User>findByRole(Role role);

      List<User> findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String name,String username);
}
