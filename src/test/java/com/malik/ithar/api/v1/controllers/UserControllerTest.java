package com.malik.ithar.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malik.ithar.api.v1.model.UserDTO;
import com.malik.ithar.api.v1.services.UserService;
import com.malik.ithar.api.v1.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void listUsers() throws Exception {

        // Given
        UserDTO user1 = UserDTO.builder()
            .firstName("Baruch")
            .lastName("Spinoza")
            .userUrl("www.test.com/spinoza")
            .build();

        UserDTO user2 = UserDTO.builder()
            .firstName("Thomas")
            .lastName("Hobbes")
            .userUrl("www.test.com/hobbes")
            .build();

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        // When/Then
        mockMvc.perform(get(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    public void getUserById() throws Exception {

        //Given
        UserDTO user = UserDTO.builder()
            .firstName("Baruch")
            .lastName("Spinoza")
            .userUrl("www.test.com/spinoza")
            .build();

        when(userService.getUserById(anyLong())).thenReturn(user);

        // When/Then
        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo("Baruch")));
    }

    @Test
    public void createNewUser() throws Exception {

        //Given
        UserDTO user = UserDTO.builder()
            .firstName("Baruch")
            .lastName("Spinoza")
            .userUrl("www.test.com/spinoza")
            .build();

        UserDTO returnDTO = new UserDTO();
        returnDTO.setFirstName(user.getFirstName());
        returnDTO.setLastName(user.getLastName());
        returnDTO.setUserUrl(user.getUserUrl());

        when(userService.createNewUser(user)).thenReturn(returnDTO);

        // When/Then
        mockMvc.perform(post(UserController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name", equalTo("Baruch")))
                .andExpect(jsonPath("$.user_url", equalTo(user.getUserUrl())));
    }

    @Test
    public void userUpdate() throws Exception {

        // Given
        UserDTO user = UserDTO.builder()
            .firstName("Baruch")
            .lastName("Spinoza")
            .userUrl("www.test.com/spinoza")
            .build();

        UserDTO returnDTO = new UserDTO();
        returnDTO.setFirstName(user.getFirstName());
        returnDTO.setLastName(user.getLastName());
        returnDTO.setUserUrl(user.getUserUrl());

        when(userService.saveByDTO(anyLong(), any(UserDTO.class))).thenReturn(returnDTO);

        // When/Then
        mockMvc.perform(put(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo("Baruch")))
                .andExpect(jsonPath("$.last_name", equalTo("Spinoza")))
                .andExpect(jsonPath("$.user_url", equalTo("www.test.com/spinoza")));
    }

    @Test
    public void deleteUser() throws Exception {

        // Given

        // When/Then
        mockMvc.perform(delete(UserController.BASE_URL + "/1123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).deleteUserById(anyLong());
    }

    @Test
    public void notFoundException() throws Exception {

        when(userService.getUserById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(UserController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}