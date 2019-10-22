package com.malik.ithar.api.v1.services;

import com.malik.ithar.api.v1.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO saveByDTO(Long id, UserDTO userDTO);

    void deleteUserById(Long id);
}
