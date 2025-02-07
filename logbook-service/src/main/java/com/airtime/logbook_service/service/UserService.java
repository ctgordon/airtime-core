package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User findById(UUID id);

    User findUserByAuthId(String userId);

    List<User> findAll();

    boolean save(User user);
}
