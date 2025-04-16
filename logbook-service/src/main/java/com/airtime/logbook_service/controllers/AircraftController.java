package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.persistence.model.Aircraft;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.crud.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/aircraft")
public class AircraftController extends CrudController<Aircraft, Integer> {
    public AircraftController(CrudService<Aircraft, Integer> service) {
        super(service);
    }
}
