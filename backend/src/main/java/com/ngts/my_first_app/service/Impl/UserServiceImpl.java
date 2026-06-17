package com.ngts.my_first_app.service.Impl;

import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.repository.UserRepository;
import com.ngts.my_first_app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("Creating User: {}", user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Fetched {} Users", users.size());
        return users;
    }

    @Override
    public User getUserById(int id) {
        log.info("Fetching User with ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(int id, User user) {
        log.info("Updating User: {}", user);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        log.warn("Deleting User with ID: {}", id);
        userRepository.deleteById(id);
    }
}
