package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.AircraftType;
import com.airtime.logbook_service.web.dto.AircraftTypeDTO;

import java.util.List;

public interface AircraftTypeService {
    List<AircraftTypeDTO> findAllAircraftTypes();

    boolean save(AircraftType aircraftType);

    AircraftType findAircraftTypeById(int id);
}
