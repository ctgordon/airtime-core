package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.UserRepository;
import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(UUID id) {
        User user = null;
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        return user;
    }

    @Override
    public User findUserByUserId(String userId) {
        return this.userRepository.findUserByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        boolean saved = false;
        try {
            this.userRepository.save(user);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }
}
