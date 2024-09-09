package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.web.dto.AtoDTO;

import java.util.List;

public interface AtoService {
    List<Ato> atoList();

    List<AtoDTO> atoDTOList();
}
