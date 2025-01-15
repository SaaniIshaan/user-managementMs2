package com.tekarch.user_managementMs2.repositories;

import com.tekarch.user_managementMs2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find User by username
    Optional<User> findByUsername(String username);

    // Custom query to find User by email
    Optional<User> findByEmail(String email);

}
