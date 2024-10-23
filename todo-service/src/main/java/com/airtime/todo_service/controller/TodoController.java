package com.airtime.todo_service.controller;

import com.airtime.todo_service.persistence.model.Person;
import com.airtime.todo_service.persistence.model.Todo;
import com.airtime.todo_service.service.PersonService;
import com.airtime.todo_service.web.dto.TodoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class TodoController {
    private final PersonService personService;

    public TodoController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "todo")
    public ResponseEntity<List<TodoDTO>> findAllTodo(@AuthenticationPrincipal OAuth2User user) {
        List<TodoDTO> todoDTOList = new ArrayList<>();
        if (user != null) {
            Person person = personService.findPersonByAuthUserId(user.getAttribute("sub"));
            if (person != null) {
                List<Todo> todoList = person.getTodoList();
                if (!todoList.isEmpty()) {
                    for (Todo todo : todoList) {
                        if (todo != null) {
                            todoDTOList.add(todo.dto());
                        }
                    }
                }
            }
        }

        return new ResponseEntity<>(todoDTOList, HttpStatus.OK);
    }
}
