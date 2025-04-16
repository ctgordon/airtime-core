package com.airtime.logbook_service.service;


import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.AircraftRepository;
import com.airtime.logbook_service.persistence.dao.TodoRepository;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.web.dto.AircraftDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("aircraftService")
public class AircraftService extends CrudServiceImpl<Aircraft, Integer> {
    public AircraftService(AircraftRepository aircraftRepository) {
        super(aircraftRepository);
    }
}
