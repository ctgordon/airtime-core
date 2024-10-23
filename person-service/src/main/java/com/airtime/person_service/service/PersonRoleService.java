package com.airtime.person_service.service;

import com.airtime.person_service.web.dto.PersonRoleDTO;

import java.util.List;

public interface PersonRoleService {
    List<PersonRoleDTO> findAllPersonRoles();
}
