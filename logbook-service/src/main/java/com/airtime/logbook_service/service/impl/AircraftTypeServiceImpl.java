package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.AircraftTypeRepository;
import com.airtime.logbook_service.persistence.model.AircraftType;
import com.airtime.logbook_service.service.AircraftTypeService;
import com.airtime.logbook_service.web.dto.AircraftTypeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("aircraftTypeService")
public class AircraftTypeServiceImpl implements AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;

    public AircraftTypeServiceImpl(AircraftTypeRepository aircraftTypeRepository) {
        this.aircraftTypeRepository = aircraftTypeRepository;
    }

    @Override
    public List<AircraftTypeDTO> findAllAircraftTypes() {
        List<AircraftTypeDTO> aircraftTypeDTOList = new ArrayList<>();
        List<AircraftType> aircraftTypeList = this.aircraftTypeRepository.findAll();

        if (!aircraftTypeList.isEmpty()) {
            aircraftTypeList.forEach(aircraftType -> aircraftTypeDTOList.add(aircraftType.dto()));
        }

        return aircraftTypeDTOList;
    }

    @Override
    public AircraftType findAircraftTypeById(int id) {
        AircraftType aircraftType = null;
        Optional<AircraftType> optional = aircraftTypeRepository.findById(id);
        if (optional.isPresent()) {
            aircraftType = optional.get();
        }
        return aircraftType;
    }

    @Override
    public boolean save(AircraftType aircraftType) {
        boolean saved = false;
        if (!aircraftTypeRepository.existsAircraftTypeByType(aircraftType.getType())) {
            try {
                this.aircraftTypeRepository.save(aircraftType);
                saved = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return saved;
    }
}
