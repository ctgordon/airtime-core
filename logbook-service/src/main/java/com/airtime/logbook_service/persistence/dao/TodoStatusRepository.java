package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airtime.logbook_service.persistence.model.TodoStatus;

public interface TodoStatusRepository extends JpaRepository<TodoStatus, Integer> {
}
