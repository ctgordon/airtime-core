package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.web.dto.TodoDTO;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    List<Todo> getRawTodos();

    List<TodoDTO> getTodos(UUID userId);

    Todo getTodo(UUID uuid);

    boolean save(Todo todo);

    boolean delete(Todo todo);
}
