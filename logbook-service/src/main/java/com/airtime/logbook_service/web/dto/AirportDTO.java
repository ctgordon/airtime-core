package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportDTO {
    private int id;
    private String countryName;
    private String countryCode;
    private String airportName;
    private String cityName;
    private String latitude;
    private String longitude;
    private String airportCode;
}
