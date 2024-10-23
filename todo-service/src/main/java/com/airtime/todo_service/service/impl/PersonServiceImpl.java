package com.airtime.todo_service.service.impl;

import com.airtime.todo_service.persistence.dao.PersonRepository;
import com.airtime.todo_service.persistence.model.Person;
import com.airtime.todo_service.service.PersonService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findPersonByAuthUserId(String userId) {
        Person person = null;
        Optional<Person> optional = personRepository.findByAuthUserId(userId);
        if (optional.isPresent()) {
            person = optional.get();
        }
        return person;
    }

}
