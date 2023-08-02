package com.sugadev.userservice.Repository;


import com.sugadev.userservice.Model.User;
import com.sugadev.userservice.Model.UserRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Rollback(false)

public class UserRepositoryTest {
    @Autowired
    public UserRepository userRepository;


    public void createTestUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("Testing");
        User newUser = new User("Test213", password);
        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser);
        assertTrue(savedUser.getIdUser() > 0);

    }

    @Test
    public void testAssignRoleToUser() {
        Integer id = 2;
        User user = userRepository.findById(id.intValue()).get();
        user.addRole(new UserRole(1));
        user.addRole(new UserRole(2));


        User updatedUser = userRepository.save(user);
        assertTrue(updatedUser.getRoles().size() == 2);

    }


//    @Test
//    public void  testAssignRoleToUser(){
//
//    }
}
