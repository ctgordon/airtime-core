package com.airtime.logbook_service.service;

import com.airtime.logbook_service.crud.CrudServiceImpl;
import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.TodoRepository;
import com.airtime.logbook_service.persistence.model.Todo;

import java.util.UUID;

@Service("todoService")
public class TodoService extends CrudServiceImpl<Todo, UUID> {
    public TodoService(TodoRepository todoRepository) {
        super(todoRepository);
    }
}
