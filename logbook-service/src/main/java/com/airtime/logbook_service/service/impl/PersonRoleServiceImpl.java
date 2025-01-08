package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.PersonRoleRepository;
import com.airtime.logbook_service.persistence.model.PersonRole;
import com.airtime.logbook_service.service.PersonRoleService;
import com.airtime.logbook_service.web.dto.PersonRoleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("personRoleService")
public class PersonRoleServiceImpl implements PersonRoleService {
    private final PersonRoleRepository personRoleRepository;

    public PersonRoleServiceImpl(PersonRoleRepository personRoleRepository) {
        this.personRoleRepository = personRoleRepository;
    }

    @Override
    public List<PersonRoleDTO> findAllPersonRoles() {
        List<PersonRoleDTO> personRoleDTOList = new ArrayList<>();
        List<PersonRole> personRoleList = this.personRoleRepository.findAll();

        if (!personRoleList.isEmpty()) {
            personRoleList.forEach(person -> personRoleDTOList.add(person.dto()));
        }

        return personRoleDTOList;
    }

    @Override
    public PersonRole findByRole(String role) {
        PersonRole personRole = null;
        Optional<PersonRole> optionalPersonRole = this.personRoleRepository.findByRole(role);
        if (optionalPersonRole.isPresent()) {
            personRole = optionalPersonRole.get();
        }
        return personRole;
    }
}
