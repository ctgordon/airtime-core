package com.airtime.logbook_service.persistence.dao;

import com.airtime.logbook_service.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
