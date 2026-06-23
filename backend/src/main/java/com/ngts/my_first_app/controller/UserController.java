package com.ngts.my_first_app.controller;

import com.ngts.my_first_app.DTO.UserRequestDTO;
import com.ngts.my_first_app.DTO.UserResponseDTO;
import com.ngts.my_first_app.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
//@Tag(name = "User API Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Tag(name = "User Write")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        log.info("[POST] Creating user: {}", request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @Tag(name = "User Read")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        log.info("[GET] Getting all users.");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Tag(name = "User Read")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        log.info("[GET] Getting user with id: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Tag(name = "User Update")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserRequestDTO request) {
        log.info("[PUT] Updating user: {}", request);
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Tag(name = "User Delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id) {
        log.info("[DELETE] Deleting user with id: {}", id);
        userService.deleteUser(id);

        return ResponseEntity.ok(Map.of(
                "message", "User deleted successfully",
                "id", id
        ));
    }
}
