package com.ngts.my_first_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngts.my_first_app.DTO.UserRequestDTO;
import com.ngts.my_first_app.DTO.UserResponseDTO;
import com.ngts.my_first_app.exception.GlobalExceptionHandler;
import com.ngts.my_first_app.exception.UserNotFoundException;
import com.ngts.my_first_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // Verifies the create endpoint returns 201 and the response body from the service.
    @Test
    void createUser_returns201Created() throws Exception {
        // Arrange
        UserRequestDTO request = buildRequest("Jane Doe", "password123", 28, "jane@test.com");
        UserResponseDTO response = buildResponse("Jane Doe", 28, "jane@test.com");

        when(userService.createUser(org.mockito.ArgumentMatchers.any(UserRequestDTO.class))).thenReturn(response);

        // Act + Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.email").value("jane@test.com"));
    }

    // Verifies the list endpoint returns 200 with all users in the response body.
    @Test
    void getUsers_returns200OkWithList() throws Exception {
        // Arrange
        when(userService.getAllUsers()).thenReturn(List.of(
                buildResponse("Jane Doe", 28, "jane@test.com"),
                buildResponse("John Doe", 31, "john@test.com")
        ));

        // Act + Assert
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Jane Doe"))
                .andExpect(jsonPath("$[1].email").value("john@test.com"));
    }

    // Verifies the get-by-id endpoint returns 200 when the service finds a user.
    @Test
    void getUserById_returns200OkWithPayload() throws Exception {
        // Arrange
        when(userService.getUserById(7)).thenReturn(buildResponse("Jane Doe", 28, "jane@test.com"));

        // Act + Assert
        mockMvc.perform(get("/api/users/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.email").value("jane@test.com"));
    }

    // Verifies the update endpoint forwards the request and returns the updated payload.
    @Test
    void updateUser_returns200OkWithUpdatedPayload() throws Exception {
        // Arrange
        UserRequestDTO request = buildRequest("Jane Doe", "password123", 28, "jane@test.com");
        when(userService.updateUser(eq(5), org.mockito.ArgumentMatchers.any(UserRequestDTO.class)))
                .thenReturn(buildResponse("Jane Doe", 28, "jane@test.com"));

        // Act + Assert
        mockMvc.perform(put("/api/users/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.email").value("jane@test.com"));
    }

    // Verifies the delete endpoint returns the success message map.
    @Test
    void deleteUser_returns200OkWithMessage() throws Exception {
        // Arrange
        doNothing().when(userService).deleteUser(12);

        // Act + Assert
        mockMvc.perform(delete("/api/users/12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted successfully"))
                .andExpect(jsonPath("$.id").value(12));
    }

    // Verifies invalid input is rejected with a 400 and field-level validation messages.
    @Test
    void createUser_withInvalidBody_returns400WithValidationMessages() throws Exception {
        // Act + Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(content().string(allOf(
                        containsString("name: Name cannot be blank."),
                        containsString("password: Password cannot be blank."),
                        containsString("age: You're too young twin. :|"),
                        containsString("email: Email cannot be blank.")
                )));
    }

    // Verifies a missing user is translated into a 404 error response by the exception handler.
    @Test
    void getUserById_whenMissing_returns404WithErrorResponse() throws Exception {
        // Arrange
        when(userService.getUserById(anyInt()))
                .thenThrow(new UserNotFoundException("User not found with id: 99"));

        // Act + Assert
        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message[0]").value("User not found with id: 99"));
    }

    private static UserRequestDTO buildRequest(String name, String password, int age, String email) {
        UserRequestDTO request = new UserRequestDTO();
        request.setName(name);
        request.setPassword(password);
        request.setAge(age);
        request.setEmail(email);
        return request;
    }

    private static UserResponseDTO buildResponse(String name, int age, String email) {
        return UserResponseDTO.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
    }
}
