package com.example.book_author.service;

import com.example.book_author.dto.LoginDto;
import com.example.book_author.dto.LoginForm;
import com.example.book_author.dto.UserDto;
import com.example.book_author.dto.UserForm;

public interface JwtService {

     UserDto register(UserForm form);

     LoginDto login(LoginForm loginDto);
}
