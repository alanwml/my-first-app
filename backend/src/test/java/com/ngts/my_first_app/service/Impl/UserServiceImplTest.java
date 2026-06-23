package com.ngts.my_first_app.service.Impl;

import com.ngts.my_first_app.DTO.UserRequestDTO;
import com.ngts.my_first_app.DTO.UserResponseDTO;
import com.ngts.my_first_app.exception.UserNotFoundException;
import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new UserRequestDTO();
        request.setName("Jane Doe");
        request.setPassword("password123");
        request.setAge(28);
        request.setEmail("jane@test.com");
    }

    // Verifies create maps request fields, saves the user, and returns the response DTO.
    @Test
    void createUser_returnsMappedResponse() {
        // Arrange
        User savedUser = buildUser(1, "Jane Doe", "password123", 28, "jane@test.com");
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        // Act
        UserResponseDTO response = userService.createUser(request);

        // Assert
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();

        assertEquals("Jane Doe", captured.getName());
        assertEquals("password123", captured.getPassword());
        assertEquals(28, captured.getAge());
        assertEquals("jane@test.com", captured.getEmail());
        assertEquals("Jane Doe", response.getName());
        assertEquals(28, response.getAge());
        assertEquals("jane@test.com", response.getEmail());
    }

    // Verifies every stored user is converted into a response DTO.
    @Test
    void getAllUsers_mapsEveryEntityToResponseDto() {
        // Arrange
        when(userRepository.findAll()).thenReturn(List.of(
                buildUser(1, "Jane Doe", "password123", 28, "jane@test.com"),
                buildUser(2, "John Doe", "secret456", 31, "john@test.com")
        ));

        // Act
        List<UserResponseDTO> response = userService.getAllUsers();

        // Assert
        assertEquals(2, response.size());
        assertEquals("Jane Doe", response.get(0).getName());
        assertEquals("jane@test.com", response.get(0).getEmail());
        assertEquals("John Doe", response.get(1).getName());
        assertEquals("john@test.com", response.get(1).getEmail());
    }

    // Verifies the service returns a user when the id exists.
    @Test
    void getUserById_returnsUserWhenFound() {
        // Arrange
        when(userRepository.findById(7)).thenReturn(Optional.of(
                buildUser(7, "Jane Doe", "password123", 28, "jane@test.com")
        ));

        // Act
        UserResponseDTO response = userService.getUserById(7);

        // Assert
        assertEquals("Jane Doe", response.getName());
        assertEquals(28, response.getAge());
        assertEquals("jane@test.com", response.getEmail());
    }

    // Verifies a missing user id produces the domain exception.
    @Test
    void getUserById_throwsUserNotFoundExceptionWhenMissing() {
        // Arrange
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99));
    }

    // Verifies update overwrites the stored entity and returns the updated response DTO.
    @Test
    void updateUser_overwritesExistingUserAndReturnsUpdatedResponse() {
        // Arrange
        User existingUser = buildUser(5, "Old Name", "oldpass", 20, "old@test.com");
        User updatedUser = buildUser(5, "Jane Doe", "password123", 28, "jane@test.com");

        when(userRepository.findById(5)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(updatedUser);

        // Act
        UserResponseDTO response = userService.updateUser(5, request);

        // Assert
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();

        assertEquals("Jane Doe", captured.getName());
        assertEquals("password123", captured.getPassword());
        assertEquals(28, captured.getAge());
        assertEquals("jane@test.com", captured.getEmail());
        assertEquals("Jane Doe", response.getName());
        assertEquals(28, response.getAge());
        assertEquals("jane@test.com", response.getEmail());
    }

    // Verifies update fails with the same not-found exception when the user does not exist.
    @Test
    void updateUser_throwsUserNotFoundExceptionWhenMissing() {
        // Arrange
        when(userRepository.findById(5)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(5, request));
    }

    // Verifies delete delegates directly to the repository.
    @Test
    void deleteUser_delegatesToRepository() {
        // Act
        userService.deleteUser(12);

        // Assert
        verify(userRepository).deleteById(12);
    }

    private static User buildUser(int id, String name, String password, int age, String email) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }
}
