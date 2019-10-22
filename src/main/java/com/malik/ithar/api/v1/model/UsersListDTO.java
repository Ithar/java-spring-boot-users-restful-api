package com.malik.ithar.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersListDTO {

    @ApiModelProperty(value="Wrapper around users", required = true)
    List<UserDTO> users;
}
