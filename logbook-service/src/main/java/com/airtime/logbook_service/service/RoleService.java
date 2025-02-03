package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
}
