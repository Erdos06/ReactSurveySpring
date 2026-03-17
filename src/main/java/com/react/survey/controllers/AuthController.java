package com.react.survey.controllers;

import com.react.survey.configs.UserAuthProvider;
import com.react.survey.dtos.user.CredentialsDto;
import com.react.survey.dtos.user.SignUpDto;
import com.react.survey.dtos.user.UserDto;
import com.react.survey.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("auth/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDTO) {
        UserDto userDTO = userService.login(credentialsDTO);

        userDTO.setToken(userAuthProvider.createToken(credentialsDTO.getUsername()));

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("auth/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDTO) {
        UserDto userDto = userService.register(signUpDTO);
        userDto.setToken(userAuthProvider.createToken(signUpDTO.getUsername()));
        return ResponseEntity.created(URI.create("/auth/register/" + userDto.getId())).body(userDto);
    }

    @GetMapping("/check/token")
    public ResponseEntity<UserDto> checkToken() {
        // Достаем аутентификацию вручную
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что юзер не аноним и авторизован
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // В твоем JwtAuthFilter ты кладешь UserDTO в Principal
        UserDto userDto = (UserDto) auth.getPrincipal();

        return ResponseEntity.ok(userDto);
    }
}
