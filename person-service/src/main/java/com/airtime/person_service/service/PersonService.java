package com.airtime.person_service.service;

import com.airtime.person_service.persistence.model.Person;
import com.airtime.person_service.web.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    List<PersonDTO> findAllPeople();

    boolean save(Person person);

    Person findPersonById(int id);

    Person findPersonByAuthUserId(String userId);

    Person findPersonByName(String name);

    Person findPersonByUuid(UUID uuid);
}
