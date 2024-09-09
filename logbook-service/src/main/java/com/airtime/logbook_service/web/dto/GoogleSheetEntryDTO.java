package com.airtime.logbook_service.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleSheetEntryDTO {
    private String date;
    private String school;
    private String type;
    private String registration;
    private String pic;
    private String from;
    private String to;
    private String takeOffs;
    private String landings;
    private String departureTime;
    private String arrivalTime;
    private String remarks;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDatetime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDatetime;
}
