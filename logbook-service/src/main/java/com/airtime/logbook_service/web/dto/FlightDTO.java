package com.airtime.logbook_service.web.dto;

import com.airtime.logbook_service.persistence.model.Ato;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {
    private int id;
    private UUID uuid;
    private AircraftDTO aircraft;
    private PersonDTO pilotInCommand;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDatetime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDatetime;
    private String remarks;
    private int takeOffs;
    private int landings;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "GMT")
    private Timestamp createdDate;
    private String updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "GMT")
    private Timestamp updatedDate;
    private int flightTimeMinutes;
    private Ato ato;
}
