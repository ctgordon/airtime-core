package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Message;

public interface MessageService {
    Message getPublicMessage();
    Message getProtectedMessage();
    Message getAdminMessage();
}
