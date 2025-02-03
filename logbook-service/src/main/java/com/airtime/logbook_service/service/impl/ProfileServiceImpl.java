package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.PersonRepository;
import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.web.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Profile> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public List<PersonDTO> findAllPeople() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<Profile> people = this.personRepository.findAll();

        if (!people.isEmpty()) {
            people = people.stream().filter(Profile::isInUse).toList();
            people.forEach(person -> personDTOList.add(person.dto()));
        }

        return personDTOList;
    }

    @Override
    public boolean save(Profile profile) {
        boolean saved = false;
        try {
            this.personRepository.save(profile);
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return saved;
    }

    @Override
    public Profile findPersonById(UUID id) {
        Profile profile = null;
        Optional<Profile> optional = personRepository.findById(id);
        if (optional.isPresent()) {
            profile = optional.get();
        }
        return profile;
    }

    @Override
    public Profile findPersonByUserId(UUID userId) {
        Profile profile = null;
        Optional<Profile> optional = Optional.ofNullable(personRepository.findByUserId(userId));
        if (optional.isPresent()) {
            profile = optional.get();
        }
        return profile;
    }

    @Override
    public Profile findPersonByName(String name) {
        Profile profile = null;
        /*Optional<Person> optional = personRepository.findPersonByNameIgnoreCase(name);
        if (optional.isPresent()) {
            person = optional.get();
        }*/
        return profile;
    }
}
