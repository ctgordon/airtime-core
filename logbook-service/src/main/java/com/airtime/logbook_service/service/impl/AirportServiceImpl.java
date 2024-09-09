package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.AirportRepository;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.service.AirportService;
import com.airtime.logbook_service.web.dto.AirportDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("airportService")
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<AirportDTO> findAllAirports() {
        List<AirportDTO> airportDTOList = new ArrayList<>();
        List<Airport> airportList = this.airportRepository.findAll();

        if (!airportList.isEmpty()) {
            airportList.forEach(airport -> airportDTOList.add(airport.dto()));
        }

        return airportDTOList;
    }

    @Override
    public boolean save(Airport airport) {
        boolean saved = false;
        if (!airportRepository.existsAirportByAirportCode(airport.getAirportCode())) {
            try {
                this.airportRepository.save(airport);
                saved = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return saved;
    }

    @Override
    public Airport findAirportById(int id) {
        Airport airport = null;
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if (optionalAirport.isPresent()) {
            airport = optionalAirport.get();
        }

        return airport;
    }

    @Override
    public Airport findAirportByAirportCode(String code) {
        return airportRepository.findAirportByAirportCode(code);
    }
}
