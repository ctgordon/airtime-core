package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.AircraftRepository;
import com.airtime.logbook_service.persistence.dao.AirportRepository;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.web.dto.AirportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("airportService")
public class AirportService extends CrudServiceImpl<Airport, Integer> {
    public AirportService(AirportRepository airportRepository) {
        super(airportRepository);
    }
}


