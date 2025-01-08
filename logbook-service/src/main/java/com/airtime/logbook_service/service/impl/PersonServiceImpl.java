package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.PersonRepository;
import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.PersonDTO;
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
    public List<PersonDTO> findAllPeople() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<Person> people = this.personRepository.findAll();

        if (!people.isEmpty()) {
            people.forEach(person -> personDTOList.add(person.dto()));
        }

        return personDTOList;
    }

    @Override
    public boolean save(Person person) {
        boolean saved = false;
        try {
            this.personRepository.save(person);
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return saved;
    }

    @Override
    public Person findPersonById(int id) {
        Person person = null;
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()) {
            person = optional.get();
        }
        return person;
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

    @Override
    public Person findPersonByUuid(UUID uuid) {
        Person person = null;
        Optional<Person> optional = personRepository.findPersonByAppUserId(uuid);
        if (optional.isPresent()) {
            person = optional.get();
        }
        return person;
    }

    @Override
    public Person findPersonByName(String name) {
        Person person = null;
        /*Optional<Person> optional = personRepository.findPersonByNameIgnoreCase(name);
        if (optional.isPresent()) {
            person = optional.get();
        }*/
        return person;
    }
}
