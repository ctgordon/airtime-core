package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.UserRoleRepository;
import com.airtime.logbook_service.persistence.model.UserRole;
import com.airtime.logbook_service.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findByUserId(UUID userId) {
        return userRoleRepository.findUserRoleByUserId(userId);
    }

    @Override
    public void save(UserRole userRole) {
        this.userRoleRepository.save(userRole);
    }
}
