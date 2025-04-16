package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import com.airtime.logbook_service.persistence.dao.FlightRepository;
import com.airtime.logbook_service.persistence.dao.ProfileRepository;
import com.airtime.logbook_service.persistence.model.Flight;
import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.web.dto.ProfileDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("profileService")
public class ProfileService extends CrudServiceImpl<Profile, UUID> {
    public ProfileService(ProfileRepository profileRepository) {
        super(profileRepository);
    }
}
