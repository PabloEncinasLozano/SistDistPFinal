package com.project.users.repository;

import com.project.users.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

	
}
