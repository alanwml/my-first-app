package com.ngts.my_first_app;

import com.ngts.my_first_app.model.User;
import com.ngts.my_first_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setName("JPA Doe");
        user.setAge(31);
        user.setEmail("jpadoe@test.com");

        Date now = new Date();

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertEquals(1, savedUser.getId());
    }
}
