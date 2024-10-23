package com.airtime.todo_service.persistence.dao;

import com.airtime.todo_service.persistence.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepository extends JpaRepository <Todo, UUID> {
}
