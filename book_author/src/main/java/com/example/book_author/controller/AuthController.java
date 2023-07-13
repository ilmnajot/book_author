package com.example.book_author.controller;

import com.example.book_author.dto.LoginDto;
import com.example.book_author.dto.LoginForm;
import com.example.book_author.dto.UserDto;
import com.example.book_author.dto.UserForm;
import com.example.book_author.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserForm form) {
        return jwtService.register(form);
    }

    @PostMapping("/login")
    public LoginDto loginToSystem(@RequestBody LoginForm loginDto) {
       return jwtService.login(loginDto);
    }

}
