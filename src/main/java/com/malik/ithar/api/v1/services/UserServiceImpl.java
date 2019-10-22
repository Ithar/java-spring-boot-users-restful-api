package com.malik.ithar.api.v1.services;

import com.malik.ithar.api.v1.mapper.UserMapper;
import com.malik.ithar.api.v1.model.UserDTO;
import com.malik.ithar.api.v1.domain.User;
import com.malik.ithar.api.v1.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        return save(userMapper.mapToUser(userDTO));
    }

    private UserDTO save(User user) {
        User savedUser = userRepository.save(user);
        return userMapper.mapToDTO(savedUser);
    }

    @Override
    public UserDTO saveByDTO(Long id, UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
        user.setId(id);
        return save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
