package com.project.users.repository;

import com.project.users.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar las operaciones de acceso a datos relacionadas con los usuarios.
 * Proporciona métodos para buscar, eliminar y verificar la existencia de usuarios por su correo electrónico.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

	
}
