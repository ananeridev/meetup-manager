package com.demo.meetupmanager.service.impl;

import com.demo.meetupmanager.exception.BusinessException;
import com.demo.meetupmanager.model.Registration;
import com.demo.meetupmanager.repository.RegistrationRepository;
import com.demo.meetupmanager.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {


    RegistrationRepository repository;

    public RegistrationServiceImpl(RegistrationRepository repository) {
        this.repository = repository;
    }

    public Registration save(Registration registration) {
        if (repository.existsByRegistration(registration.getRegistration())) {
            throw new BusinessException("Registration Already created");
        }
        return repository.save(registration);
    }
}
