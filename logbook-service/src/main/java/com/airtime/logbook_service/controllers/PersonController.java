package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Flight;
import com.airtime.logbook_service.persistence.model.Goal;
import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;
import com.airtime.logbook_service.web.dto.GoalDTO;
import com.airtime.logbook_service.web.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class PersonController {

    private final PersonService personService;
    private final FlightService flightService;

    public PersonController(PersonService personService, FlightService flightService) {
        this.personService = personService;
        this.flightService = flightService;
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

            if (!person.getGoals().isEmpty()) {
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
            }
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
    public ResponseEntity<String> addPerson(@RequestBody @Validated PersonDTO personDTO) {
        boolean saved = false;

        try {
            Person person = personService.findPersonById(personDTO.getId());
            if (person != null) {
                person.setName(personDTO.getName());
                person.setMoniker(personDTO.getMoniker());
                person.setPersonRole(personDTO.getPersonRole());
                saved = personService.save(person);
            } else {
                Person newPerson = new Person();
                newPerson.setName(personDTO.getName());
                newPerson.setMoniker(personDTO.getMoniker());
                newPerson.setPersonRole(personDTO.getPersonRole());
                saved = personService.save(newPerson);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
