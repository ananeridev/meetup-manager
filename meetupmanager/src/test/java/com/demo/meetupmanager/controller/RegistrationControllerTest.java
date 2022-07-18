package com.demo.meetupmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.demo.meetupmanager.config.ApplicationConfig;
import com.demo.meetupmanager.controller.dto.RegistrationDTO;
import com.demo.meetupmanager.controller.web.RegistrationController;
import com.demo.meetupmanager.model.Registration;
import com.demo.meetupmanager.service.RegistrationService;

@ActiveProfiles("test")
@Import(value = { ApplicationConfig.class, RegistrationController.class })
@WebMvcTest(controllers = { RegistrationController.class })
public class RegistrationControllerTest {

        static String REGISTRATION_PATH = "/registration";

        @Autowired
        MockMvc mockMvc;

        @MockBean
        RegistrationService registrationService;

        @Test
        @DisplayName("Should create a registration with success")
        public void createRegistrationTest() throws Exception {

                // cenario
                RegistrationDTO registrationDTOBuilder = createNewRegistration();
                Registration savedRegistration = Registration.builder().id(101)
                                .name("Ana Neri").dateOfRegistration("10/10/2021").registration("001").build();

                // execucao
                BDDMockito.given(registrationService.save(any(Registration.class))).willReturn(savedRegistration);

                String json = new ObjectMapper().writeValueAsString(registrationDTOBuilder);

                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                .post(REGISTRATION_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json);

                // verificacao, assert....
                mockMvc
                                .perform(request)
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("id").value(101))
                                .andExpect(jsonPath("name").value(registrationDTOBuilder.getName()))
                                .andExpect(jsonPath("dateOfRegistration")
                                                .value(registrationDTOBuilder.getDateOfRegistration()))
                                .andExpect(jsonPath("registration").value(registrationDTOBuilder.getRegistration()));
        }

        private RegistrationDTO createNewRegistration() {
                return RegistrationDTO.builder().id(101).name("Ana Neri").dateOfRegistration("10/10/2021")
                                .registration("001")
                                .build();
        }
}
