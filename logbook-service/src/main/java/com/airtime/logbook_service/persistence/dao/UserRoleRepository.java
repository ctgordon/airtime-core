package com.airtime.logbook_service.persistence.dao;

import com.airtime.logbook_service.persistence.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findUserRoleByUserId(UUID userId);
}
