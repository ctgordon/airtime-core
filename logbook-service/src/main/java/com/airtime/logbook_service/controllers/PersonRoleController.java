package com.airtime.logbook_service.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.PersonRoleService;
import com.airtime.logbook_service.web.dto.PersonRoleDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class PersonRoleController {
    private final PersonRoleService personRoleService;

    public PersonRoleController(PersonRoleService personRoleService) {
        this.personRoleService = personRoleService;
    }

    @GetMapping(value = "/api/person-roles")
    public List<PersonRoleDTO> findAllPersonRoles() {
        return personRoleService.findAllPersonRoles();
    }
}
