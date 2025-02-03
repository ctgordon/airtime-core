package com.airtime.logbook_service.service;

import com.airtime.logbook_service.web.dto.UserProfileDTO;

import java.util.List;

public interface UserProfileService {
    List<UserProfileDTO> findAllUserProfiles();
}
