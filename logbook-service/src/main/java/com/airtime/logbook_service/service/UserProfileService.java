package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.dao.UserProfileRepository;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.persistence.model.UserProfile;
import com.airtime.logbook_service.service.impl.CrudServiceImpl;
import com.airtime.logbook_service.web.dto.UserProfileDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userProfileService")
public class UserProfileService extends CrudServiceImpl<UserProfile, UUID> {
    public UserProfileService(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
    }
}
