package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.service.ProfileService;
import com.airtime.logbook_service.service.TodoService;
import com.airtime.logbook_service.service.TodoStatusService;
import com.airtime.logbook_service.web.dto.TodoDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api/airtime/")
public class TodoController {
    private final TodoService todoService;
    private final ProfileService profileService;
    private final TodoStatusService todoStatusService;

    public TodoController(TodoService todoService, ProfileService profileService, TodoStatusService todoStatusService) {
        this.todoService = todoService;
        this.profileService = profileService;
        this.todoStatusService = todoStatusService;
    }

    /*@GetMapping(value = "todo")
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
    }*/

    @GetMapping(value = "todo/{id}")
    public ResponseEntity<TodoDTO> findTodoById(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") final UUID id) {
        TodoDTO todoDTO = null;
        if (user != null) {
            todoDTO = todoService.getTodo(id).dto();
        }

        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @PostMapping(value = "todo")
    public ResponseEntity<String> addTodo(@RequestBody @Validated TodoDTO todoDTO, @AuthenticationPrincipal OAuth2User user) {
        boolean saved = false;

        Todo existingTodo = null;

        if (todoDTO.getId() != null) {
            existingTodo = todoService.getTodo(todoDTO.getId());
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());


        /*if (user != null) {
            Person person = personService.findPersonByAuthUserId(user.getAttribute("sub"));
            if (person != null) {
                if (existingTodo != null) {
                    existingTodo.setDescription(todoDTO.getDescription());
                    existingTodo.setTodoStatus(todoDTO.getTodoStatus());
                    existingTodo.setUpdatedBy(person.getAppUserId());
                    existingTodo.setOwnerId(person.getAppUserId());
                    existingTodo.setUpdatedDate(timestamp);
                    saved = todoService.save(existingTodo);
                } else {
                    Todo todo = new Todo();
                    todo.setId(UUID.randomUUID());
                    todo.setDescription(todoDTO.getDescription());
                    todo.setTodoStatus(todoDTO.getTodoStatus());
                    todo.setCreatedBy(person.getAppUserId());
                    todo.setCreatedDate(timestamp);
                    todo.setUpdatedBy(person.getAppUserId());
                    todo.setUpdatedDate(timestamp);
                    todo.setOwnerId(person.getAppUserId());
                    saved = todoService.save(todo);
                }
            }
        }*/

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Todo not saved", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "todo/{id}")
    public ResponseEntity<String> deleteTodo(@AuthenticationPrincipal OAuth2User user, @PathVariable("id") final UUID id) {
        boolean deleted = false;

        Todo existingTodo = todoService.getTodo(id);

        if (user != null) {
            /*Person person = personService.findPersonByAuthUserId(user.getAttribute("sub"));
            if (person != null) {
                if (existingTodo != null) {
                    deleted = todoService.delete(existingTodo);
                }
            }*/
        }

        return (deleted) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Todo not deleted", HttpStatus.BAD_REQUEST);
    }
}
