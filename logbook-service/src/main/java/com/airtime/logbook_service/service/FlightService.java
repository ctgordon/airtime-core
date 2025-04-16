package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.AtoRepository;
import com.airtime.logbook_service.persistence.dao.FlightRepository;
import com.airtime.logbook_service.persistence.model.*;
import com.airtime.logbook_service.web.dto.FlightDTO;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("flightService")
public class FlightService extends CrudServiceImpl<Flight, Integer> {
    public FlightService(FlightRepository flightRepository) {
        super(flightRepository);
    }
}
