package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import com.airtime.logbook_service.persistence.model.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private int id;
    private String name;
    private String moniker;
    private PersonRole personRole;
    private PersonAttribute personAttribute;
    private UUID uuid;
    private String emailAddress;
    private String authEmailAddress;
}
