package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.persistence.model.TodoStatus;
import com.airtime.logbook_service.crud.CrudService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/todo-status")
public class TodoStatusController extends CrudController<TodoStatus, Integer> {
    protected TodoStatusController(CrudService<TodoStatus, Integer> service) {
        super(service);
    }
}
