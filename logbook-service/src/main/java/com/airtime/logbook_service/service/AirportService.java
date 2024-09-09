package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.web.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> findAllAirports();

    boolean save(Airport airport);

    Airport findAirportById(int id);

    Airport findAirportByAirportCode(String code);
}
