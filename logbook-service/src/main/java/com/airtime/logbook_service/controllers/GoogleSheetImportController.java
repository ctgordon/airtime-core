package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.persistence.model.Flight;
import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.AircraftService;
import com.airtime.logbook_service.service.AirportService;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.GoogleSheetEntryDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class GoogleSheetImportController {
    private final FlightService flightService;
    private final PersonService personService;
    private final AircraftService aircraftService;

    private final AirportService airportService;

    public GoogleSheetImportController(FlightService flightService, PersonService personService, AircraftService aircraftService, AirportService airportService) {
        this.flightService = flightService;
        this.personService = personService;
        this.aircraftService = aircraftService;
        this.airportService = airportService;
    }

    @PostMapping(value = "/api/google-entries/")
    public ResponseEntity<String> addPerson(@RequestBody @Validated List<GoogleSheetEntryDTO> googleSheetEntryDTOList) {
        boolean saved = false;


        try {
            if (!googleSheetEntryDTOList.isEmpty()) {
                googleSheetEntryDTOList.forEach(entry -> {
                    Flight flight = new Flight();

                    flight.setDepartureDatetime(entry.getDepartureDatetime());
                    flight.setArrivalDatetime(entry.getArrivalDatetime());

                    Person person = null;

                    if (entry.getPic() != null) {
                        Person existingPerson = personService.findPersonByName(entry.getPic());

                        if (existingPerson != null) {
                            person = existingPerson;
                        }
                    }

                    flight.setPerson(person);

                    Aircraft aircraft = null;

                    if (entry.getRegistration() != null) {
                        Aircraft existingAircraft = aircraftService.findAircraftByRegistration(entry.getRegistration());

                        if (existingAircraft != null) {
                            aircraft = existingAircraft;
                        }
                    }

                    flight.setAircraft(aircraft);

                    Airport departureAirport = null;

                    if (entry.getFrom() != null) {
                        Airport existingAirport = airportService.findAirportByAirportCode(entry.getFrom());

                        if (existingAirport != null) {
                            departureAirport = existingAirport;
                        }
                    }

                    flight.setDepartureAirport(departureAirport);

                    Airport arrivalAirport = null;

                    if (entry.getTo() != null) {
                        Airport existingAirport = airportService.findAirportByAirportCode(entry.getTo());

                        if (existingAirport != null) {
                            arrivalAirport = existingAirport;
                        }
                    }

                    flight.setArrivalAirport(arrivalAirport);

                    int takeOffs = 1;

                    if (entry.getTakeOffs() != null && !entry.getTakeOffs().equals("")) {
                        takeOffs = Integer.parseInt(entry.getTakeOffs());
                    }

                    flight.setTakeOffs(takeOffs);

                    int landings = 1;

                    if (entry.getLandings() != null && !entry.getLandings().equals("")) {
                        landings = Integer.parseInt(entry.getLandings());
                    }

                    flight.setLandings(landings);

                    flight.setRemarks(entry.getRemarks());

                    // System.out.println(flight);

                    this.flightService.save(flight);
                });
            }
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
