package com.react.survey.mappers.user;

import com.react.survey.dtos.survey.SurveyDTO;
import com.react.survey.dtos.user.SignUpDTO;
import com.react.survey.dtos.user.UserDTO;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toUserDto(User user);
    public User toUser(UserDTO userDto);

    @Mapping(target = "password", ignore = true)
    public User signUpToUser(SignUpDTO userDTO);
}
