package com.ngts.my_first_app;

import com.ngts.my_first_app.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;



public class UserTest {

    @Test
    void testUserFieldsGood() {
        // Arrange
        User user = new User();

        // Act
        user.setName("John Doe");
        user.setAge(30);
        user.setEmail("johndoe@test.com");

        Date now = new Date();
        user.setRegistrationDate(now);

        // Assert
        assertEquals("John Doe", user.getName());
        assertEquals(30, user.getAge());
        assertEquals("johndoe@test.com", user.getEmail());
        assertEquals(now, user.getRegistrationDate());
    }
}
