package com.ngts.my_first_app.controller;

import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("[POST] Creating user: {}", user);
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("[GET] Getting all users.");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        log.info("[GET] Getting user with id: {}", id);
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        log.info("[PUT] Updating user: {}", user);
        return userService.updateUser(id, user);
    }
//
//    @PatchMapping

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable int id) {
        log.info("[DELETE] Deleting user with id: {}", id);
        userService.deleteUser(id);

        return Map.of(
                "message", "User deleted successfully",
                "id", id
        );
    }
}
