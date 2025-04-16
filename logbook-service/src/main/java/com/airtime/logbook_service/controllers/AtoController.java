package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.crud.CrudService;
import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.persistence.model.Todo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.AtoService;
import com.airtime.logbook_service.web.dto.AtoDTO;

import java.util.List;
import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/ato")
public class AtoController extends CrudController<Ato, Integer> {
    public AtoController(CrudService<Ato, Integer> service) {
        super(service);
    }
}
