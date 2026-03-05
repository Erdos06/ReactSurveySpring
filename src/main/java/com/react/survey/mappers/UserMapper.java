package com.react.survey.mappers;

import com.react.survey.dtos.SignUpDTO;
import com.react.survey.dtos.UserDTO;
import com.react.survey.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    public User signUpToUser(SignUpDTO userDTO);
}
