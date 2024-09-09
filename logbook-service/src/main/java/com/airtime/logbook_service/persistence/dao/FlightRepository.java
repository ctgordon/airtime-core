package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.persistence.model.Flight;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> getFlightsByDepartureDatetimeAfterAndDepartureDatetimeBefore(Timestamp departureDatetime, Timestamp departureDatetime2);

    Flight findTopByOrderByDepartureDatetimeDesc();

    Flight findTopByAndAtoEqualsOrderByDepartureDatetimeDesc(Ato ato);

    Flight findByUuid(UUID uuid);

    List<Flight> findFlightByOwnerId(UUID ownerId);
}
