package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.RoleRepository;
import com.airtime.logbook_service.persistence.model.Role;
import com.airtime.logbook_service.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }
}
