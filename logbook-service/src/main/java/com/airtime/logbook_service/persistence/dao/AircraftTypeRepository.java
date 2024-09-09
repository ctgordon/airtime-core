package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.AircraftType;

@Repository
public interface AircraftTypeRepository extends JpaRepository<AircraftType, Integer> {
    boolean existsAircraftTypeByType(String type);
}
