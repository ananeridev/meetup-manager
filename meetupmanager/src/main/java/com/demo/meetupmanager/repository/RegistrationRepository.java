package com.demo.meetupmanager.repository;

import com.demo.meetupmanager.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    boolean existsByRegistration(String registration);

    Optional<Registration> findByRegistration(String registrationAtrb);
}
