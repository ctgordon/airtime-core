package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.model.Message;
import com.airtime.logbook_service.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Override
    public Message getPublicMessage() {
        final var text = "This is a public message.";

        return Message.from(text);
    }

    @Override
    public Message getProtectedMessage() {
        final var text = "This is a protected message.";

        return Message.from(text);
    }

    @Override
    public Message getAdminMessage() {
        final var text = "This is an admin message.";

        return Message.from(text);
    }
}
