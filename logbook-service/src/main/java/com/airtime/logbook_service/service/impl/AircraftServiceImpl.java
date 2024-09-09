package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.AircraftRepository;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.service.AircraftService;
import com.airtime.logbook_service.web.dto.AircraftDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("aircraftService")
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public List<AircraftDTO> findAllAircraft() {
        List<AircraftDTO> aircraftDTOList = new ArrayList<>();
        List<Aircraft> aircraftList = aircraftRepository.findAll();

        if (!aircraftList.isEmpty()) {
            aircraftList.forEach(aircraft -> aircraftDTOList.add(aircraft.dto()));
        }

        return aircraftDTOList;
    }

    @Override
    public void save(Aircraft aircraft) {
        this.aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft findAircraftById(int id) {
        Aircraft aircraft = null;
        Optional<Aircraft> optionalAircraft = aircraftRepository.findById(id);
        if (optionalAircraft.isPresent()) {
            aircraft = optionalAircraft.get();
        }

        return aircraft;
    }

    @Override
    public Aircraft findAircraftByRegistration(String registration) {
        Aircraft aircraft = null;
        Optional<Aircraft> optionalAircraft = aircraftRepository.findAircraftByTailNumberIgnoreCase(registration);
        if (optionalAircraft.isPresent()) {
            aircraft = optionalAircraft.get();
        }

        return aircraft;
    }
}
