package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import com.airtime.logbook_service.persistence.model.*;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private UUID id;
    private String forename;
    private String surname;
    private String knownAs;
    private Collection<UserRole> personRoles;
    private PersonAttribute personAttribute;
    private UUID appUserId;
    private String appEmailAddress;
    private String authEmailAddress;
    private boolean inUse;
    private User user;
}
