package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.AircraftRepository;
import com.airtime.logbook_service.persistence.dao.AircraftTypeRepository;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.persistence.model.AircraftType;
import com.airtime.logbook_service.web.dto.AircraftTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("aircraftTypeService")
public class AircraftTypeService extends CrudServiceImpl<AircraftType, Integer> {
    public AircraftTypeService(AircraftTypeRepository aircraftRepository) {
        super(aircraftRepository);
    }
}
