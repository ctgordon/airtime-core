package com.airtime.person_service.controllers;

import com.airtime.person_service.persistence.model.Person;
import com.airtime.person_service.service.PersonService;
import com.airtime.person_service.web.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person")
    public ResponseEntity<PersonDTO> getProfile(@AuthenticationPrincipal OAuth2User user) {
        PersonDTO personDTO = null;
        if (user != null) {
            Person person = personService.findPersonByAuthUserId(user.getAttribute("sub"));
            if (person != null) {
                personDTO = person.dto();
            }
        }

        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
}
