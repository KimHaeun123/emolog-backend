package com.emolog.repository;

import com.emolog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("하은");
        user.setEmail("2091127@hansung.ac.kr");
        user.setPassword("암호화예정");
        userRepository.save(user);
    }
}
