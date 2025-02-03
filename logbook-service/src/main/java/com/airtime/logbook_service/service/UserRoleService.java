package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    List<UserRole> findByUserId(UUID personId);

    boolean save(UserRole userRole);

    boolean delete(UserRole userRole);
}
