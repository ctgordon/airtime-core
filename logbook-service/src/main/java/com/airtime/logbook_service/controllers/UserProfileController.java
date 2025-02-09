package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.UserProfile;
import com.airtime.logbook_service.service.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/user-profile")
public class UserProfileController extends CrudController<UserProfile, UUID> {
    public UserProfileController(CrudService<UserProfile, UUID> service) {
        super(service);
    }
}
