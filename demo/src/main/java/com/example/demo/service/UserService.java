package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User updateUser(User user) { return userRepository.save(user);}

    public long sizeOfAllUsers() {
        return userRepository.count();
    }

    public List<User> findAllUsersByName(String name) {
        return userRepository.nameSearch(name);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> getUserNamesByPrefix(String prefix) {
        return userRepository.findByNameStartingWith(prefix);
    }

    public List<User> getUsersWithinAgeRange(int start, int end) {
        return userRepository.findByAgeBetween(start, end);
    }
}
