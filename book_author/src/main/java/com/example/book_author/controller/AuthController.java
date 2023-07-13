package com.example.book_author.controller;

import com.example.book_author.dto.LoginDto;
import com.example.book_author.dto.UserDto;
import com.example.book_author.dto.UserForm;
import com.example.book_author.entity.User;
import com.example.book_author.security.JwtProvider;
import com.example.book_author.security.JwtService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/register")
    public HttpEntity<UserDto> registerUser(@RequestBody UserForm form) {
        return ResponseEntity.ok(jwtService.registerUser(form));
    }
    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));
        User user = (User) authenticated.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }

}
