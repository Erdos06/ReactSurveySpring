package com.react.survey.controllers;

import com.react.survey.configs.UserAuthProvider;
import com.react.survey.dtos.CredentialsDTO;
import com.react.survey.dtos.SignUpDTO;
import com.react.survey.dtos.UserDTO;
import com.react.survey.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO) {
        UserDTO userDTO = userService.login(credentialsDTO);

        userDTO.setToken(userAuthProvider.createToken(credentialsDTO.getUsername()));

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDto = userService.register(signUpDTO);
        userDto.setToken(userAuthProvider.createToken(signUpDTO.getUsername()));
        return ResponseEntity.created(URI.create("/auth/register/" + userDto.getId())).body(userDto);
    }
}
