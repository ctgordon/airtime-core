package com.airtime.todo_service.service;


import com.airtime.todo_service.persistence.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    Person findPersonByAuthUserId(String userId);
}
