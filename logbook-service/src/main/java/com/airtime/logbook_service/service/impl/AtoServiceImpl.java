package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.AtoRepository;
import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.service.AtoService;
import com.airtime.logbook_service.web.dto.AtoDTO;

import java.util.ArrayList;
import java.util.List;

@Service("atoService")
public class AtoServiceImpl implements AtoService {
    private final AtoRepository atoRepository;

    public AtoServiceImpl(AtoRepository atoRepository) {
        this.atoRepository = atoRepository;
    }

    @Override
    public List<Ato> atoList() {
        return this.atoRepository.findAll();
    }

    @Override
    public List<AtoDTO> atoDTOList() {
        List<AtoDTO> atoDTOList = new ArrayList<>();
        List<Ato> atoList = this.atoRepository.findAll();
        if (!atoList.isEmpty()) {
            atoList.stream().filter(Ato::isInUse).forEach(ato -> atoDTOList.add(ato.dto()));
        }
        return atoDTOList;
    }
}
