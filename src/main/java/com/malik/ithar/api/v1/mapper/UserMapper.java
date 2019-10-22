package com.malik.ithar.api.v1.mapper;

import com.malik.ithar.api.v1.domain.User;
import com.malik.ithar.api.v1.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "url", target = "userUrl")
    UserDTO mapToDTO(User user);

    @Mapping(source = "userUrl", target = "url")
    User mapToUser(UserDTO userDTO);
}
