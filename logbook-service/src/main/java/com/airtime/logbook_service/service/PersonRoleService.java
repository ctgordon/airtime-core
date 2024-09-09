package com.airtime.logbook_service.service;

import com.airtime.logbook_service.web.dto.PersonRoleDTO;

import java.util.List;

public interface PersonRoleService {
    List<PersonRoleDTO> findAllPersonRoles();
}
