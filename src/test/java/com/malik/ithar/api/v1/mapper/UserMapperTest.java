package com.malik.ithar.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import com.malik.ithar.api.v1.model.UserDTO;
import com.malik.ithar.api.v1.domain.User;
import org.junit.Test;

public class UserMapperTest {

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void mapToDTO() {

        // Given
        String firstName = "Bob";
        String lastName = "Marley";
        String url = "www.bob-marley.com";

        User user = User.builder()
            .firstName(firstName)
            .lastName(lastName)
            .url(url)
            .build();

        // When
        UserDTO userDTO = userMapper.mapToDTO(user);

        // Then
        assertEquals(firstName, userDTO.getFirstName());
        assertEquals(lastName, userDTO.getLastName());
        assertEquals(url, userDTO.getUserUrl());
    }

    @Test
    public void mapToUser() {

        // Given
        String firstName = "Bob";
        String lastName = "Marley";
        String url = "www.bob-marley.com";

        UserDTO userDTO = UserDTO.builder()
            .firstName(firstName)
            .lastName(lastName)
            .userUrl(url)
            .build();

        // When
        User user = userMapper.mapToUser(userDTO);

        // Then
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(url, user.getUrl());
    }

}