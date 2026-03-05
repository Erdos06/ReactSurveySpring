package com.react.survey.services;

import com.react.survey.dtos.CredentialsDTO;
import com.react.survey.dtos.SignUpDTO;
import com.react.survey.dtos.UserDTO;
import com.react.survey.entities.User;
import com.react.survey.exceptions.AppException;
import com.react.survey.mappers.UserMapper;
import com.react.survey.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO findByLogin(String login) {
        User user = userRepository.findByUsername(login).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return userMapper.toUserDto(user);
    }

    public UserDTO login(CredentialsDTO credentialsDTO){
        User user = userRepository.findByUsername(credentialsDTO.getUsername()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())){
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(SignUpDTO userDTO){
        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());

        if(optionalUser.isPresent()){
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);

    }

}
