package com.quickbite_backend.quickbite_backend.repository;

import com.quickbite_backend.quickbite_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);

    // For Admin Dashboard
  List<User> findByRoleIgnoreCase(String role);
long countByRoleIgnoreCase(String role);

}
