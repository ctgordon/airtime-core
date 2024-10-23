package com.airtime.person_service.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private boolean addCorsFilter;
    private String allowedOrigins;
    private String version;
    private String audience;
    private String clientOriginUrl;

    @NestedConfigurationProperty
    private EmailProperties email;

}
