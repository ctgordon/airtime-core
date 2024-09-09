package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.TodoStatusService;
import com.airtime.logbook_service.web.dto.TodoStatusDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api/airtime/")
public class TodoStatusController {
    private final TodoStatusService todoStatusService;

    public TodoStatusController(TodoStatusService todoStatusService) {
        this.todoStatusService = todoStatusService;
    }

    @GetMapping(value = "todo-status")
    public ResponseEntity<List<TodoStatusDTO>> findAllTodoStatus() {
        return new ResponseEntity<>(todoStatusService.getTodoStatusDTOList(), HttpStatus.OK);
    }
}
