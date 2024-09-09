package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airtime.logbook_service.persistence.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
