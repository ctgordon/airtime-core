package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.TodoRepository;
import com.airtime.logbook_service.persistence.model.Todo;
import com.airtime.logbook_service.service.TodoService;
import com.airtime.logbook_service.web.dto.TodoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getRawTodos() {
        return todoRepository.findAll();
    }

    @Override
    public List<TodoDTO> getTodos() {
        List<TodoDTO> todoDTOList = new ArrayList<>();
        List<Todo> todoList = todoRepository.findAll();

        if (!todoList.isEmpty()) {
            todoList.forEach(todo -> {
                todoDTOList.add(todo.dto());
            });
        }

        return todoDTOList;
    }

    @Override
    public Todo getTodo(UUID uuid) {
        Todo todo = null;

        Optional<Todo> optionalTodo = todoRepository.findById(uuid);

        if (optionalTodo.isPresent()) {
            todo = optionalTodo.get();
        }

        return todo;
    }

    @Override
    public boolean save(Todo todo) {
        boolean saved = false;
        try {
            this.todoRepository.save(todo);
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return saved;
    }

    @Override
    public boolean delete(Todo todo) {
        boolean deleted = false;
        try {
            this.todoRepository.delete(todo);
            deleted = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return deleted;
    }
}
