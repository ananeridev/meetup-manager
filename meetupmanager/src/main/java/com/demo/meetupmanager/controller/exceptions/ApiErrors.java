package com.demo.meetupmanager.controller.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import com.demo.meetupmanager.controller.dto.FieldDTO;
import com.demo.meetupmanager.exception.BusinessException;

import lombok.Getter;

@Getter
public class ApiErrors {
    private final List<Object> errors;

    public ApiErrors(BindingResult bindingResult) {
        this.errors = bindingResult.getAllErrors().stream()
                .map(error -> new FieldDTO(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    public ApiErrors(BusinessException e) {
        this.errors = Arrays.asList(e.getMessage());
    }

    public ApiErrors(ResponseStatusException e) {
        this.errors = Arrays.asList(e.getReason());
    }

}
