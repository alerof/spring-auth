package com.example.alaerof.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserApp, UUID> {
    Optional<UserApp> findByEmail(String email);
}

