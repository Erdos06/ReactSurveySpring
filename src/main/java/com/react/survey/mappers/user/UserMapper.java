package com.react.survey.mappers.user;

import com.react.survey.dtos.user.SignUpDto;
import com.react.survey.dtos.user.UserDto;
import com.react.survey.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDto toUserDto(User user);
    public User toUser(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    public User signUpToUser(SignUpDto userDTO);
}
