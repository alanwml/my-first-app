package com.ngts.my_first_app.service;

import com.ngts.my_first_app.model.User;
import java.util.List;

// This is the Service Layer Interface for User related operations
// Basically adds the `methods` that can be called in UserService,
// but the Implementation code is in /impl part.
public interface UserService {
    // C. Creation Methods.
    User createUser(User user);

    // R. Reading Methods.
    List<User> getAllUsers();
    User getUserById(int id);

    // U. Updating Methods.
    User updateUser(int id, User user);

    // D. Deleting Methods.
    void deleteUser(int id);
}