package com.project.users.repository;

import com.project.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
