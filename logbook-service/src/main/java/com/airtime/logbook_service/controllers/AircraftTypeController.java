package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.persistence.model.AircraftType;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.crud.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/aircraft-type")
public class AircraftTypeController extends CrudController<AircraftType, Integer> {
    public AircraftTypeController(CrudService<AircraftType, Integer> service) {
        super(service);
    }
}
