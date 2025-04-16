package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.crud.CrudService;
import com.airtime.logbook_service.persistence.model.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/profile")
public class ProfileController extends CrudController<Profile, UUID> {
    public ProfileController(CrudService<Profile, UUID> service) {
        super(service);
    }
}
