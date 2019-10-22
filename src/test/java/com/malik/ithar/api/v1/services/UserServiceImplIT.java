package com.malik.ithar.api.v1.services;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import com.malik.ithar.api.v1.mapper.UserMapper;
import com.malik.ithar.api.v1.model.UserDTO;
import com.malik.ithar.bootstrap.Bootstrap;
import com.malik.ithar.api.v1.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceImplIT {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        new Bootstrap(userRepository).run();
        userService = new UserServiceImpl(UserMapper.INSTANCE, userRepository);
    }

    @Test
    public void saveUserFirstName() {
        String testFirstName = "Dummy Name";
        Long id = getFirstUserId();

        UserDTO originalUser = userService.getUserById(id);
        assertNotNull(originalUser);

        String lastName = originalUser.getLastName();
        String url = originalUser.getUserUrl();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(testFirstName);
        userDTO.setLastName(lastName);
        userDTO.setUserUrl(url);

        userService.saveByDTO(id, userDTO);

        UserDTO savedUser = userService.getUserById(id);

        assertEquals(id, savedUser.getId());
        assertEquals(testFirstName, savedUser.getFirstName());
        assertEquals(lastName, savedUser.getLastName());
        assertEquals(url, savedUser.getUserUrl());
    }

    private Long getFirstUserId(){
        return userRepository.findAll().get(0).getId();
    }
}
