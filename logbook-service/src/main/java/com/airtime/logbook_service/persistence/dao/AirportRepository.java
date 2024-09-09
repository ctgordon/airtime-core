package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    boolean existsAirportByAirportCode(String airportCode);

    Airport findAirportByAirportCode(String airportCode);
}
