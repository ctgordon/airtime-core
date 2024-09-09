package com.airtime.logbook_service.web.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthDTO {
    private String message;
}
