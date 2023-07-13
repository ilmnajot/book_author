package com.example.book_author.service;

import com.example.book_author.dto.AuthorDto;
import com.example.book_author.dto.AuthorForm;
import com.example.book_author.entity.SuccessDto;

import java.util.List;

public interface AuthorService {
    AuthorDto add(AuthorForm form);

    AuthorDto getOne(Long id);

    List<AuthorDto> getAll();

    AuthorDto update(Long id, AuthorForm form);

    SuccessDto delete(Long id);

    AuthorDto getDeletedOne(Long id);
    List<AuthorDto> getAllDeleted();
}
