package com.airtime.logbook_service.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.persistence.model.Message;
import com.airtime.logbook_service.service.MessageService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/public")
  public Message getPublic() {
    return messageService.getPublicMessage();
  }

  @GetMapping("/protected")
  public Message getProtected() {
    return messageService.getProtectedMessage();
  }

  @GetMapping("/admin")
  public Message getAdmin() {
    return messageService.getAdminMessage();
  }
}
