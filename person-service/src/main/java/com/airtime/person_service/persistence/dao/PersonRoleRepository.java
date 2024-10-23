package com.airtime.person_service.persistence.dao;

import com.airtime.person_service.persistence.model.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole, Integer> {
}
