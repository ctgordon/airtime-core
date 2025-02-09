package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.dao.TodoStatusRepository;
import com.airtime.logbook_service.persistence.model.TodoStatus;
import com.airtime.logbook_service.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Repository;

@Repository("todoStatusService")
public class TodoStatusService extends CrudServiceImpl<TodoStatus, Integer> {
    public TodoStatusService(TodoStatusRepository todoStatusRepository) {
        super(todoStatusRepository);
    }
}
