package com.airtime.logbook_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.*;
import com.airtime.logbook_service.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("api/airtime/")
public class AirtimeController {
    private final ProfileService profileService;
    private final FlightService flightService;
    private final AircraftService aircraftService;
    private final AircraftTypeService aircraftTypeService;
    private final AirportService airportService;
    private final AtoService atoService;

    public AirtimeController(ProfileService profileService,
                             FlightService flightService,
                             AircraftService aircraftService,
                             AirportService airportService,
                             AtoService atoService,
                             AircraftTypeService aircraftTypeService
    ) {
        this.profileService = profileService;
        this.flightService = flightService;
        this.aircraftService = aircraftService;
        this.airportService = airportService;
        this.atoService = atoService;
        this.aircraftTypeService = aircraftTypeService;
    }

    /*@GetMapping(value = "person")
    public PersonDTO getPerson() {
        Person person = this.personService.findPersonById(1);
        return person.dto();
    }*/

    /*@GetMapping(value = "flights")
    public List<FlightDTO> getFlights(@AuthenticationPrincipal OAuth2User user) {
        System.out.println(user);
        List<FlightDTO> flightDTOList = null;
        List<Flight> flights = this.flightService.getAllFlights();
        if (flights != null && !flights.isEmpty()) {
            flightDTOList = this.flightService.getAllFlightsDTO(flights);
        }
        return flightDTOList;
    }*/

    @GetMapping(value = "aircraft")
    public List<AircraftDTO> getAircraft() {
        return this.aircraftService.findAllAircraft();
    }

    @GetMapping(value = "aircraft-types")
    public List<AircraftTypeDTO> getAircraftTypes() {
        return this.aircraftTypeService.findAllAircraftTypes();
    }

    @GetMapping(value = "airports")
    public List<AirportDTO> getAirports() {
        return this.airportService.findAllAirports();
    }

    @GetMapping(value = "atos")
    public List<AtoDTO> getAtos() {
        return this.atoService.atoDTOList();
    }

    @GetMapping(value = "people")
    public List<ProfileDTO> getPeople() {
        return this.profileService.findAllPeople();
    }
}
