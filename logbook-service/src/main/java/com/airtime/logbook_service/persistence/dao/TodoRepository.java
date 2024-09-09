package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airtime.logbook_service.persistence.model.Todo;

import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
