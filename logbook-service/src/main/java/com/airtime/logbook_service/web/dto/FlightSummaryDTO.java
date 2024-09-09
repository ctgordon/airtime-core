package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSummaryDTO {
    private String totalHours;
    private String totalHoursDual;
    private String totalHoursPIC;
    private int totalFlights;
    private FlightDTO lastFlight;
}
