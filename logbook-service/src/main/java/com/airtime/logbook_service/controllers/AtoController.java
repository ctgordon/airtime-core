package com.airtime.logbook_service.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.AtoService;
import com.airtime.logbook_service.web.dto.AtoDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class AtoController {

    private final AtoService atoService;

    public AtoController(AtoService atoService) {
        this.atoService = atoService;
    }

    @GetMapping(value = "/api/atos")
    public List<AtoDTO> findAllAtos() {
        return atoService.atoDTOList();
    }

}
