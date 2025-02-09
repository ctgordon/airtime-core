package com.airtime.logbook_service.persistence.dao;

import com.airtime.logbook_service.persistence.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
