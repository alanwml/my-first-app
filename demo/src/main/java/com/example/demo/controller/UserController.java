package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @ResponseBody
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "userid") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User u = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        if (!userService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        User u = userService.updateUser(user);
        return ResponseEntity.ok(u);
    }

    @ResponseBody
    @GetMapping("/size")
    public ResponseEntity<Long> getSize(){
        return ResponseEntity.ok(userService.sizeOfAllUsers());
    }

    @ResponseBody
    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name){
        List<User> users = userService.findAllUsersByName(name);
        if (users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(users);
        }
    }
}
