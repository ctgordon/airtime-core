package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import com.airtime.logbook_service.persistence.model.AircraftType;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftDTO {
    private int id;
    private String tailNumber;
    private AircraftType aircraftType;
}
