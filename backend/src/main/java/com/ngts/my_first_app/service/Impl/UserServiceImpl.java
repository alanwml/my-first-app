package com.ngts.my_first_app.service.Impl;

import com.ngts.my_first_app.DTO.UserRequestDTO;
import com.ngts.my_first_app.DTO.UserResponseDTO;
import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.repository.UserRepository;
import com.ngts.my_first_app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {

        // 1. Convert RequestDTO → Entity
        log.debug("[DEBUG] - Converting RequestDTO to Entity.");
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());

        // 2. Save Entity to DB
        log.debug("[DEBUG] - Saving Entity to DB.");
        User savedUser = userRepository.save(user);

        // 3. Convert Entity → ResponseDTO
        // 4. & Return ResponseDTO
        log.debug("[DEBUG] - Converting Entity to ResponseDTO.");
        return UserResponseDTO.builder()
                .name(savedUser.getName())
                .age(savedUser.getAge())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        log.debug("[DEBUG] - Getting All Users.");

        // Get All → Stream → [MAP] Build ResponseDTO → Return List
        return userRepository.findAll().stream()
                .map(user -> UserResponseDTO.builder()
                        .name(user.getName())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .build())
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(int id) {

        log.debug("[DEBUG] - Getting User with id: {}", id);

        // Find User By ID → [MAP] Build ResponseDTO → Return
        // → ELSE Throw Exception.
        return userRepository.findById(id)
                .map(user -> UserResponseDTO.builder()
                        .name(user.getName())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public UserResponseDTO updateUser(int id, UserRequestDTO request) {

        // 1. Find the current User by ID.
        log.debug("[DEBUG] - Finding User with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        log.debug("[DEBUG] - User Found: {}", user);

        // 2. Update current User with new Request details.
        log.debug("[DEBUG] - Overwriting Request Details over current User.");
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());

        // 3. Save updated User into DB.
        log.debug("[DEBUG] - Saving Updated User Entity to DB.");
        User savedUser = userRepository.save(user);

        // 4. Convert Entity → ResponseDTO
        // 5. & Return ResponseDTO
        log.debug("[DEBUG] - Converting Updated User Entity to ResponseDTO.");
        return UserResponseDTO.builder()
                .name(savedUser.getName())
                .age(savedUser.getAge())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public void deleteUser(int id) {
        log.warn("Deleting User with ID: {}", id);
        userRepository.deleteById(id);
    }
}
