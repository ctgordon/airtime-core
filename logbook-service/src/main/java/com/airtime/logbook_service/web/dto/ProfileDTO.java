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
public class ProfileDTO {
    private UUID id;
    private String forename;
    private String surname;
    private String knownAs;
    private UUID userId;
    private boolean inUse;
}
