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
    public boolean save(UserRole userRole) {
        boolean saved = false;
        try {
            this.userRoleRepository.save(userRole);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }

    @Override
    public boolean delete(UserRole userRole) {
        boolean deleted = false;
        try {
            this.userRoleRepository.delete(userRole);
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
