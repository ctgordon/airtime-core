package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.web.dto.HealthDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class HealthController {
    @GetMapping(value = "/health")
    public HealthDTO health() {
        HealthDTO healthDTO = new HealthDTO();
        healthDTO.setMessage("Hello world");
        return healthDTO;
    }
}
