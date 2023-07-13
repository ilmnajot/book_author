package com.example.book_author.service.impl;

import com.example.book_author.dto.LoginDto;
import com.example.book_author.dto.LoginForm;
import com.example.book_author.dto.UserDto;
import com.example.book_author.dto.UserForm;
import com.example.book_author.entity.User;
import com.example.book_author.enums.RoleName;
import com.example.book_author.exception.AppException;
import com.example.book_author.repository.UserRepository;
import com.example.book_author.security.JwtProvider;
import com.example.book_author.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public UserDto register(UserForm form) {
        boolean user = userRepository.existsByUsernameAndDeletedFalse(form.getUsername());
        if (user) {
            throw new AppException("there is already exists user with " + form.getUsername());
        }
        User user1 = new User();
        user1.setFirstName(form.getFirstName());
        user1.setFirstName(form.getLastName());
        user1.setLastName(form.getLastName());
        user1.setUsername(form.getUsername());
        user1.setPassword(passwordEncoder.encode(form.getPassword()));
        user1.setRoleName(RoleName.USER);
        User savedUser = userRepository.save(user1);
        return UserDto.toUserDto(savedUser);
    }

    @Override
    public LoginDto login(LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        ));
        String token = jwtProvider.generateToken(form.getUsername());
        LoginDto dto = new LoginDto();
        dto.setToken(token);
        return dto;
    }
}
