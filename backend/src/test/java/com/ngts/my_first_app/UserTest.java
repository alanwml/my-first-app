package com.ngts.my_first_app;

import com.ngts.my_first_app.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    // Verifies the entity stores and exposes its basic fields correctly.
    @Test
    void testUserFieldsGood() {
        // Arrange
        User user = new User();

        // Act
        user.setName("John Doe");
        user.setPassword("password123");
        user.setAge(30);
        user.setEmail("johndoe@test.com");

        // Assert
        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals(30, user.getAge());
        assertEquals("johndoe@test.com", user.getEmail());
    }
}
