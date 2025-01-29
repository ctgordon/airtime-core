package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/api/people")
    public List<PersonDTO> findAllPeople() {
        return personService.findAllPeople();
    }

    @GetMapping(value = "/api/private/person")
    public ResponseEntity<PersonDTO> person(Authentication authentication) {
        System.out.println(authentication.getAuthorities());
        Person person = null;
        //Person person = personService.findPersonByAuthUserId(authentication.getName());
        return (person != null ? new ResponseEntity<>(person.dto(), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/api/person/{uuid}")
    public PersonDTO findPersonById(@PathVariable("uuid") final UUID uuid) {
        Person person = personService.findPersonByUuid(uuid);
        PersonDTO personDTO = null;
        if (person != null) {
            personDTO = person.dto();
        }
        return personDTO;
    }

    @DeleteMapping(value = "/api/person/{uuid}")
    public ResponseEntity<String> deletePerson(@PathVariable("uuid") final UUID uuid) {
        boolean deleted = true;
        return (deleted ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Failed to remove", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/api/person")
    public ResponseEntity<String> addPerson(@RequestBody @Validated PersonDTO personDTO, Authentication authentication) {
        boolean saved = false;
        UUID uuid = UUID.randomUUID();

        /*try {
            Person person = personService.findPersonByAuthUserId(authentication.getName());
            if (person != null) {
                person.setForename(personDTO.getForename());
                person.setSurname(personDTO.getSurname());
                person.setKnownAs(personDTO.getKnownAs());
                saved = personService.save(person);
            } else {
                //ToDo - use more elegant solution for attributing role
                //PersonRole personRole = this.personRoleService.findByRole("Basic");
                Person newPerson = new Person();
                newPerson.setForename(personDTO.getForename());
                newPerson.setKnownAs(personDTO.getKnownAs());
                //newPerson.setPersonRole(personRole);
                saved = personService.save(newPerson);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("Sausage");
        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.NOT_MODIFIED);
    }
}
