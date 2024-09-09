package com.airtime.logbook_service.service;


import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.web.dto.AircraftDTO;

import java.util.List;

public interface AircraftService {
    List<AircraftDTO> findAllAircraft();

    void save(Aircraft aircraft);

    Aircraft findAircraftById(int id);

    Aircraft findAircraftByRegistration(String registration);
}
