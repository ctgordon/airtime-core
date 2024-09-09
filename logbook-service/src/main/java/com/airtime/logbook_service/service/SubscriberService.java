package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Subscriber;

import java.util.List;

public interface SubscriberService {
    List<Subscriber> subscriberList();
}
