package com.airtime.person_service.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledEmailService {
    private final MailService mailService;

    public ScheduledEmailService(MailService mailService) {
        this.mailService = mailService;
    }
}
