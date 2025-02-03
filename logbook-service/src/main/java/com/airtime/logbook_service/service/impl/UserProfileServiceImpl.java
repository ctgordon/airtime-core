package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.service.ProfileService;
import com.airtime.logbook_service.service.UserProfileService;
import com.airtime.logbook_service.service.UserService;
import com.airtime.logbook_service.web.dto.UserProfileDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
    private final UserService userService;
    private final ProfileService profileService;

    public UserProfileServiceImpl(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @Override
    public List<UserProfileDTO> findAllUserProfiles() {
        List<UserProfileDTO> userProfileDTOList = new ArrayList<>();
        List<User> userList = userService.findAll();

        if (userList != null && !userList.isEmpty()) {
            userList.forEach(user -> {
                Profile profile = profileService.findPersonByUserId(user.getId());
                if (profile != null) {
                    UserProfileDTO userProfileDTO = UserProfileDTO
                            .builder()
                            .user(user)
                            .profile(profile)
                            .build();
                    userProfileDTOList.add(userProfileDTO);
                }
            });
        }

        return userProfileDTOList;
    }
}
