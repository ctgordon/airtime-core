package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.service.AirportService;
import com.airtime.logbook_service.web.dto.AirportDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /*
    "countryName": "Luxembourg",
    "countryCode": "LUX",
    "airportName": "Luxembourg",
    "cityName": "Luxembourg",
    "latitude": 49.623333333333335,
    "longitude": 6.204444444444444,
    "airportCode": "ELLX",
     */

    @GetMapping(value = "/api/airports")
    public List<AirportDTO> findAllAircraft() {
        return airportService.findAllAirports();
    }

    @DeleteMapping(value = "/api/airport/{airportId}")
    public ResponseEntity<String> deleteAircraft(@PathVariable("airportId") final Integer airportId) {
        boolean deleted = true;
        return (deleted ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Failed to remove", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/api/airport/")
    public ResponseEntity<String> addAircraft(@RequestBody @Validated AirportDTO airportDTO) {
        boolean saved = false;

        try {
            Airport airport = new Airport();
            airport.setCountryName(airportDTO.getCountryName());
            airport.setCountryCode(airportDTO.getCountryCode());
            airport.setAirportName(airportDTO.getAirportName());
            airport.setAirportCode(airportDTO.getAirportCode());
            airport.setCityName(airportDTO.getCityName());
            airport.setLatitude(airportDTO.getLatitude());
            airport.setLongitude(airportDTO.getLongitude());
            saved = airportService.save(airport);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
