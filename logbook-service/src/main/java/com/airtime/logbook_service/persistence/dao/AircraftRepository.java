package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.Aircraft;

import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    Optional<Aircraft> findAircraftByTailNumberIgnoreCase(String registration);
}
