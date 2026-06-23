package com.ngts.my_first_app;

import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // Verifies the repository can persist a valid user and populate generated fields.
    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setName("JPA Doe");
        user.setPassword("secret123");
        user.setAge(31);
        user.setEmail("jpadoe@test.com");

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertTrue(savedUser.getId() > 0);
        assertEquals("JPA Doe", savedUser.getName());
        assertEquals("secret123", savedUser.getPassword());
        assertEquals(31, savedUser.getAge());
        assertEquals("jpadoe@test.com", savedUser.getEmail());
        assertNotNull(savedUser.getRegistrationDate());
    }
}
