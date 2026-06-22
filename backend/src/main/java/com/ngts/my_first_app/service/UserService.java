package com.ngts.my_first_app.service;

import com.ngts.my_first_app.DTO.UserRequestDTO;
import com.ngts.my_first_app.DTO.UserResponseDTO;
import com.ngts.my_first_app.model.User;
import java.util.List;

// This is the Service Layer Interface for User related operations
// Basically adds the `methods` that can be called in UserService,
// but the Implementation code is in /impl part.
public interface UserService {
    // C. Creation Methods.
    UserResponseDTO createUser(UserRequestDTO request);

    // R. Reading Methods.
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(int id);

    // U. Updating Methods.
    UserResponseDTO updateUser(int id, UserRequestDTO request);

    // D. Deleting Methods.
    void deleteUser(int id);
}