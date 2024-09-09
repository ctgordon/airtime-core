package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.service.AircraftService;
import com.airtime.logbook_service.web.dto.AircraftDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping(value = "/api/aircraft")
    public List<AircraftDTO> findAllAircraft() {
        return aircraftService.findAllAircraft();
    }

    @DeleteMapping(value = "/api/aircraft/{aircraftId}")
    public ResponseEntity<String> deleteAircraft(@PathVariable("aircraftId") final Integer aircraftId) {
        boolean deleted = true;
        return (deleted ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Failed to remove", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/api/aircraft/")
    public ResponseEntity<String> addAircraft(@RequestBody @Validated AircraftDTO aircraftDTO) {
        boolean saved = false;

        try {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftType(aircraftDTO.getAircraftType());
            aircraft.setTailNumber(aircraftDTO.getTailNumber());
            aircraftService.save(aircraft);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
