package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.service.PersonRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class PersonController {

    private final PersonService personService;
    private final FlightService flightService;
    private final PersonRoleService personRoleService;

    public PersonController(PersonService personService, FlightService flightService, PersonRoleService personRoleService) {
        this.personService = personService;
        this.flightService = flightService;
        this.personRoleService = personRoleService;
    }

    @GetMapping(value = "/api/people")
    public List<PersonDTO> findAllPeople() {
        return personService.findAllPeople();
    }

    @GetMapping(value = "/api/person")
    public PersonDTO person(Authentication authentication) {
        PersonDTO personDTO = new PersonDTO();
        Person person = personService.findPersonByAuthUserId(authentication.getName());

        if (person != null) {
            personDTO = person.dto();

            /*if (!person.getGoals().isEmpty()) {
                for (Goal goal : person.getGoals()) {
                    GoalDTO goalDTO = goal.dto();
                    List<Flight> flightListFromGoal = flightService.getFlightsFromGoal(goal);
                    if (!flightListFromGoal.isEmpty()) {
                        FlightSummaryDTO flightSummaryDTO = flightService.getFlightSummary(flightListFromGoal);
                        if (flightSummaryDTO != null) {
                            goalDTO.setFlightSummary(flightSummaryDTO);
                        }
                    }
                }
            }*/
        }

        return personDTO;
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

        try {
            Person person = personService.findPersonByAuthUserId(authentication.getName());
            if (person != null) {
                person.setForename(personDTO.getForename());
                person.setSurname(personDTO.getSurname());
                person.setKnownAs(personDTO.getKnownAs());
                person.setAppEmailAddress(personDTO.getAppEmailAddress());
                //person.setPersonRole(personDTO.getPersonRole());
                //person.setAppUserId(uuid);
                //person.getPersonAttribute().setId(person.getAppUserId());
                person.setPersonAttribute(personDTO.getPersonAttribute());
                saved = personService.save(person);
            } else {
                //ToDo - use more elegant solution for attributing role
                //PersonRole personRole = this.personRoleService.findByRole("Basic");
                Person newPerson = new Person();
                newPerson.setAuthEmailAddress(personDTO.getAppEmailAddress());
                newPerson.setForename(personDTO.getForename());
                newPerson.setKnownAs(personDTO.getKnownAs());
                //newPerson.setPersonRole(personRole);
                saved = personService.save(newPerson);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Sausage");
        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.NOT_MODIFIED);
    }
}
