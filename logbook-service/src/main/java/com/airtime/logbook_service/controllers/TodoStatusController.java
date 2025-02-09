package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.persistence.model.TodoStatus;
import com.airtime.logbook_service.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.TodoStatusService;
import com.airtime.logbook_service.web.dto.TodoStatusDTO;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/todo-status")
public class TodoStatusController extends CrudController<TodoStatus, Integer> {
    protected TodoStatusController(CrudService<TodoStatus, Integer> service) {
        super(service);
    }
}
