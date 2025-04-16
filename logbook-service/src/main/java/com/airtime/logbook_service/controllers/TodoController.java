package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.crud.CrudController;
import com.airtime.logbook_service.crud.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Todo;

import java.util.UUID;

@Secured("ROLE_SUPER_ADMIN")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/v1/todo")
public class TodoController extends CrudController<Todo, UUID> {
    public TodoController(CrudService<Todo, UUID> service) {
        super(service);
    }
}
