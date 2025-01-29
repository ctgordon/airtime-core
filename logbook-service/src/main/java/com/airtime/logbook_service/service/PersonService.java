package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.web.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    List<Person> findAll();
    List<PersonDTO> findAllPeople();

    boolean save(Person person);

    Person findPersonById(int id);

    Person findPersonByName(String name);

    Person findPersonByUuid(UUID uuid);
}
