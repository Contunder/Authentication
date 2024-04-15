package com.microservice.authentification.domain.ports;

import com.microservice.authentification.domain.entities.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthentificationPorts extends JpaRepository<Authentification, Long> {

    Optional<Authentification> findByEmail(String email);
    Boolean existsByEmail(String email);

}
