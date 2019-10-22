package com.malik.ithar.api.v1.controllers;

import com.malik.ithar.api.v1.services.UserService;
import com.malik.ithar.api.v1.model.UserDTO;
import com.malik.ithar.api.v1.model.UsersListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/users";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsersListDTO listUsers(){
        return new UsersListDTO(userService.getAllUsers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestBody UserDTO userDTO){
        return userService.createNewUser(userDTO);
    }

    // UPDATE
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.saveByDTO(id, userDTO);
    }

    // DELETE
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
