package com.helloworlds.authentication.infrastructure.dao;

import com.helloworlds.authentication.infrastructure.entity.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationDAO extends JpaRepository<Authentification, Long> {

    Optional<Authentification> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndPassword(String email, String password);

}
