package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Goal;
import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.service.GoalService;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.GoalDTO;

import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class GoalController {
    private final GoalService goalService;
    private final FlightService flightService;
    private final PersonService personService;

    public GoalController(GoalService goalService, FlightService flightService, PersonService personService) {
        this.goalService = goalService;
        this.flightService = flightService;
        this.personService = personService;
    }

   /* @GetMapping(value = "/api/goals")
    public List<GoalDTO> getGoals(Authentication authentication) {
        List<GoalDTO> goalDTOList = new ArrayList<>();
        Person person = personService.findPersonByAuthUserId(authentication.getName());

        if (person != null) {
            if (!person.getGoals().isEmpty()) {
                person.getGoals().forEach(goal -> {
                    goalDTOList.add(goal.dto());
                });
            }
        }

        return goalDTOList;
    }*/

    @PostMapping(value = "/api/goal")
    public ResponseEntity<String> saveGoal(Authentication authentication, @RequestBody @Validated GoalDTO goalDTO) {
        boolean saved = false;

        Person person = personService.findPersonByAuthUserId(authentication.getName());

        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());

        try {
            Goal goal = new Goal();
            goal.setPersonId(person.getId());
            goal.setStartDate(goalDTO.getStartDate());
            goal.setEndDate(goalDTO.getEndDate());
            goal.setHoursRequired(goalDTO.getHoursRequired());
            goal.setInUse(goalDTO.isInUse());
            goal.setCreatedBy(person.getForename());
            goal.setCreatedDate(timestamp);
            goal.setUpdatedBy(person.getForename());
            goal.setUpdatedDate(timestamp);
            saved = goalService.save(goal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
