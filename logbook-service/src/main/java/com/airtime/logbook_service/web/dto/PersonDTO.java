package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import com.airtime.logbook_service.persistence.model.*;

import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private int id;
    private String forename;
    private String surname;
    private String knownAs;
    private PersonRole personRole;
    private PersonAttribute personAttribute;
    private UUID appUserId;
    private String appEmailAddress;
    private String authEmailAddress;
}
