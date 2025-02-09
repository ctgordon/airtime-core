package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.web.dto.TodoDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/todo")
public class TodoController extends CrudController<Todo, UUID> {
    public TodoController(CrudService<Todo, UUID> service) {
        super(service);
    }

    /*@GetMapping(value = "/api/private/todo")
    public ResponseEntity<List<TodoDTO>> findAllTodo(@AuthenticationPrincipal OAuth2User user) {
        List<TodoDTO> todoDTOList = null;
        User appUser = userService.findUserByAuthId(user.getAttribute("sub"));
        if (appUser != null) {
            todoDTOList = todoService.getTodos(appUser.getId());
        }

        return new ResponseEntity<>(todoDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "todo/{id}")
    public ResponseEntity<TodoDTO> findTodoById(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") final UUID id) {
        TodoDTO todoDTO = null;
        if (user != null) {
            todoDTO = todoService.getTodo(id).dto();
        }

        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }*/
}
