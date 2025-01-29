package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.*;
import com.airtime.logbook_service.web.dto.FlightDTO;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    FlightDTO getFlight(UUID uuid);

    List<Flight> getAllFlights();

    List<Flight> getAllFlightsByOwner(Person person);

    List<FlightDTO> getAllFlightsDTO(List<Flight> flights);

    boolean save(Flight flight);

    Flight findFlightById(int id);

    void deleteFlight(Flight flight);

    FlightDTO getLatestFlight();

    FlightDTO getLatestFlightByAto(Ato ato);

    FlightSummaryDTO getFlightSummary(List<Flight> flightList);

    List<Flight> getFlightsBetween();

    //List<FlightDTO> getFlightsFromCustomReport(CustomReport customReport);

    List<Flight> getFlightsFromGoal(Goal goal);
}
