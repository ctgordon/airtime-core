package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.dao.TodoStatusRepository;
import com.airtime.logbook_service.persistence.model.TodoStatus;
import com.airtime.logbook_service.service.TodoStatusService;
import com.airtime.logbook_service.web.dto.TodoStatusDTO;

import java.util.ArrayList;
import java.util.List;

@Repository("todoStatusService")
public class TodoStatusServiceImpl implements TodoStatusService {
    private final TodoStatusRepository todoStatusRepository;

    public TodoStatusServiceImpl(TodoStatusRepository todoStatusRepository) {
        this.todoStatusRepository = todoStatusRepository;
    }

    @Override
    public List<TodoStatusDTO> getTodoStatusDTOList() {
        List<TodoStatusDTO> todoStatusDTOList = new ArrayList<>();

        List<TodoStatus> todoStatusList = todoStatusRepository.findAll();

        if (!todoStatusList.isEmpty()) {
            todoStatusList.forEach(todoStatus -> todoStatusDTOList.add(todoStatus.dto()));
        }

        return todoStatusDTOList;
    }
}
