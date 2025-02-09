package com.airtime.logbook_service.web.dto;

import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.persistence.model.User;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private UUID id;
    private User user;
    private Profile profile;
}
