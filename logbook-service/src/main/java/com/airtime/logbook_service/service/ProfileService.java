package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.web.dto.ProfileDTO;

import java.util.List;
import java.util.UUID;

public interface ProfileService {
    List<Profile> findAll();
    List<ProfileDTO> findAllPeople();

    boolean save(Profile profile);

    Profile findPersonById(UUID id);

    Profile findPersonByUserId(UUID userId);
    Profile findPersonByName(String name);
}
