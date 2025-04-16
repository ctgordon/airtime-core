package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.AirportRepository;
import com.airtime.logbook_service.persistence.dao.AtoRepository;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.web.dto.AtoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("atoService")
public class AtoService extends CrudServiceImpl<Ato, Integer> {
    public AtoService(AtoRepository atoRepository) {
        super(atoRepository);
    }
}
