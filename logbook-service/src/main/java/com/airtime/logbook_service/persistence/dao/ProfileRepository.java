package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.Profile;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Profile, UUID> {
    Profile findByUserId(UUID userId);
}
