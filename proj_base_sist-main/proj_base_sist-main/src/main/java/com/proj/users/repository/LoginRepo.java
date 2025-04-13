package com.example.books.books.repository;

import com.frontend_folder.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface loginRepo extends JpaRepository<Login, Long> {
}
