package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.SubscriberRepository;
import com.airtime.logbook_service.persistence.model.Subscriber;
import com.airtime.logbook_service.service.SubscriberService;

import java.util.List;

@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<Subscriber> subscriberList() {
        return subscriberRepository.findAll();
    }
}
