package com.demo.meetupmanager.controller.web;

import com.demo.meetupmanager.controller.dto.RegistrationDTO;
import com.demo.meetupmanager.model.Registration;
import com.demo.meetupmanager.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    private ModelMapper modelMapper;


    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationDTO create(@RequestBody @Valid RegistrationDTO dto) {

        Registration entity = modelMapper.map(dto, Registration.class);
        entity = registrationService.save(entity);

        return modelMapper.map(entity, RegistrationDTO.class);
    }
}

