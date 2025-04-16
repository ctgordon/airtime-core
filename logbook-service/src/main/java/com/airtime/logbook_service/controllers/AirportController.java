package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.persistence.model.Airport;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.crud.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/airport")
public class AirportController extends CrudController<Airport, Integer> {
    public AirportController(CrudService<Airport, Integer> service) {
        super(service);
    }
}
