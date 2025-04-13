package com.proj.users.repository;

import com.proj.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
