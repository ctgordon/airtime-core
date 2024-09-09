package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.AircraftType;
import com.airtime.logbook_service.service.AircraftTypeService;
import com.airtime.logbook_service.web.dto.AircraftTypeDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class AircraftTypeController {
    private final AircraftTypeService aircraftTypeService;

    public AircraftTypeController(AircraftTypeService aircraftTypeService) {
        this.aircraftTypeService = aircraftTypeService;
    }

    @GetMapping(value = "/api/aircraft-types")
    public List<AircraftTypeDTO> findAllAircraft() {
        return aircraftTypeService.findAllAircraftTypes();
    }

    @PostMapping(value = "/api/aircraft-types/")
    public ResponseEntity<String> addAircraft(@RequestBody @Validated AircraftTypeDTO aircraftTypeDTO) {
        boolean saved = false;

        try {
            AircraftType aircraftType = aircraftTypeService.findAircraftTypeById(aircraftTypeDTO.getId());
            if (aircraftType != null) {
                aircraftType.setType(aircraftTypeDTO.getType());
                saved = aircraftTypeService.save(aircraftType);
            } else {
                AircraftType newAircraftType = new AircraftType();
                newAircraftType.setType(aircraftTypeDTO.getType());
                saved = aircraftTypeService.save(newAircraftType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
