package com.airtime.person_service.web.dto;

import com.airtime.person_service.persistence.model.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

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
    private List<Membership> memberships;
    private UUID uuid;
    private String emailAddress;
    private String authEmailAddress;
    private List<Licence> licences;
}
