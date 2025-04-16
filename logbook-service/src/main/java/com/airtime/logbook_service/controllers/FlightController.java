package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.crud.CrudService;
import com.airtime.logbook_service.persistence.model.Flight;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/v1/flight")
public class FlightController extends CrudController<Flight, Integer> {
    public FlightController(CrudService<Flight, Integer> service) {
        super(service);
    }
}
