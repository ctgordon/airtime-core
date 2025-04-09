package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.service.ProfileService;
import com.airtime.logbook_service.web.dto.ProfileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class PersonController {

    private final ProfileService profileService;

    public PersonController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(value = "/api/private/people")
    public List<ProfileDTO> findAllPeople() {
        return profileService.findAllPeople();
    }

    @GetMapping(value = "/api/private/person")
    public ResponseEntity<ProfileDTO> person(Authentication authentication) {
        System.out.println(authentication.getAuthorities());
        Profile profile = null;
        //Person person = personService.findPersonByAuthUserId(authentication.getName());
        return (profile != null ? new ResponseEntity<>(profile.dto(), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/api/person/{uuid}")
    public ResponseEntity<String> deletePerson(@PathVariable("uuid") final UUID uuid) {
        boolean deleted = true;
        return (deleted ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Failed to remove", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/api/person")
    public ResponseEntity<String> addPerson(@RequestBody @Validated ProfileDTO profileDTO, Authentication authentication) {
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
