package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.Person;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsPersonByName(String name);

    Optional<Person> findPersonByNameIgnoreCase(String name);

    Optional<Person> findPersonByUuid(UUID uuid);

    Optional<Person> findByAuthUserId(String userId);
}
