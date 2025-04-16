package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.dao.UserProfileRepository;
import com.airtime.logbook_service.persistence.model.UserProfile;
import com.airtime.logbook_service.crud.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userProfileService")
public class UserProfileService extends CrudServiceImpl<UserProfile, UUID> {
    public UserProfileService(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
    }
}
