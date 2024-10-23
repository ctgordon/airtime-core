package com.airtime.person_service.spring.config.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailProperties {

    private Boolean send = Boolean.FALSE;
    private String from = "blah@test.com";

}
