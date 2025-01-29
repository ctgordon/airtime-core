package com.airtime.logbook_service.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.airtime.logbook_service.persistence.model.*;
import com.airtime.logbook_service.service.*;
import com.airtime.logbook_service.web.dto.FlightDTO;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/airtime/")
public class FlightController {
    private final FlightService flightService;
    private final AircraftService aircraftService;
    private final PersonService personService;
    private final AirportService airportService;
    private final ClientRegistration registration;

    public FlightController(ClientRegistrationRepository registrations, FlightService flightService, AircraftService aircraftService, PersonService personService, AirportService airportService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.flightService = flightService;
        this.aircraftService = aircraftService;
        this.personService = personService;
        this.airportService = airportService;
    }

    @GetMapping(value = "/api/flights/{uuid}")
    public FlightDTO findFlight(@PathVariable UUID uuid) {
        return flightService.getFlight(uuid);
    }

    @GetMapping(value = "flights")
    public ResponseEntity<List<FlightDTO>> findAllFlights(@AuthenticationPrincipal OAuth2User user) {
        List<FlightDTO> flightDTOList = null;
        /*if (user != null) {
            Person person = personService.findPersonByAuthUserId(user.getAttribute("sub"));
            if (person != null) {
                List<Flight> flightList = flightService.getAllFlightsByOwner(person);
                if (!flightList.isEmpty()) {
                    flightDTOList = flightService.getAllFlightsDTO(flightList);
                }
            }
        }*/

        return (flightDTOList != null) ? new ResponseEntity<>(flightDTOList, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/api/flight-summary")
    public FlightSummaryDTO summary() {
        FlightSummaryDTO flightSummaryDTO = null;
        List<Flight> flightList = flightService.getAllFlights();
        if (flightList != null && !flightList.isEmpty()) {
            flightSummaryDTO = flightService.getFlightSummary(flightList);
        }
        return flightSummaryDTO;
    }

    @GetMapping(value = "/api/latest-flight")
    public FlightDTO lastFlight() {
        return flightService.getLatestFlight();
    }

    @GetMapping(value = "/api/flight-summary/between")
    public FlightSummaryDTO summaryBetween() {
        FlightSummaryDTO flightSummaryDTO = null;
        List<Flight> flightList = flightService.getFlightsBetween();
        if (flightList != null && !flightList.isEmpty()) {
            flightSummaryDTO = flightService.getFlightSummary(flightList);
        }
        return flightSummaryDTO;
    }

    @PostMapping(value = "/api/flight/")
    public ResponseEntity<String> addFlight(@RequestBody @Validated FlightDTO flightDTO) {
        boolean saved = false;

        Flight flight;

        Flight existingFlight = flightService.findFlightById(flightDTO.getId());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        if (existingFlight == null) {
            flight = new Flight();
            flight.setUuid(UUID.randomUUID());
            flight.setCreatedBy("chris.gordon");
            flight.setCreatedDate(timestamp);

        } else {
            flight = existingFlight;
        }

        try {
            if (flightDTO.getAircraft() != null) {
                Aircraft aircraft = aircraftService.findAircraftById(flightDTO.getAircraft().getId());
                if (aircraft != null) {
                    flight.setAircraft(aircraft);
                }
            }

            /*if (flightDTO.getPilotInCommand() != null) {
                Person person = personService.findPersonById(flightDTO.getPilotInCommand().getId());
                flight.setPerson(person);
            }*/

            if (flight.getAto() != null) {
                flight.setAto(flight.getAto());
            }

            if (flightDTO.getDepartureAirport() != null) {
                Airport departureAirport = airportService.findAirportById(flightDTO.getDepartureAirport().getId());

                if (departureAirport != null) {
                    flight.setDepartureAirport(departureAirport);
                }
            }

            if (flightDTO.getArrivalAirport() != null) {
                Airport arrivalAirport = airportService.findAirportById(flightDTO.getArrivalAirport().getId());

                if (arrivalAirport != null) {
                    flight.setArrivalAirport(arrivalAirport);
                }
            }

            if (flightDTO.getDepartureDatetime() != null) {
                flight.setDepartureDatetime(flightDTO.getDepartureDatetime());
            }

            if (flightDTO.getArrivalDatetime() != null) {
                flight.setArrivalDatetime(flightDTO.getArrivalDatetime());
            }

            if (flightDTO.getDepartureDatetime() != null && flightDTO.getArrivalDatetime() != null) {
                long diff = flightDTO.getArrivalDatetime().getTime() - flightDTO.getDepartureDatetime().getTime();

                long diffMinutes = diff / (60 * 1000);

                flight.setFlightTimeMinutes((int) diffMinutes);
            }

            flight.setLandings(flightDTO.getLandings());
            flight.setTakeOffs(flightDTO.getTakeOffs());

            if (flightDTO.getRemarks() != null) {
                flight.setRemarks(flightDTO.getRemarks());
            }


            Person person = personService.findPersonByName("Christopher Gordon");

            if (person != null) {
                flight.setOwnerId(person.getAppUserId());
            }

            flight.setUpdatedBy("chris.gordon");
            flight.setUpdatedDate(timestamp);

            saved = flightService.save(flight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Flight not saved", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/api/flight/")
    public ResponseEntity<String> deleteFlight(@RequestBody @Validated FlightDTO flightDTO) {
        boolean deleted = false;

        try {
            Flight flight = flightService.findFlightById(flightDTO.getId());

            if (flight != null) {
                flightService.deleteFlight(flight);
            }

            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (deleted) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Flight not deleted", HttpStatus.BAD_REQUEST);
    }
}
