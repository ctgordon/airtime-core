package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftTypeDTO {
    private int id;
    private String type;
}
