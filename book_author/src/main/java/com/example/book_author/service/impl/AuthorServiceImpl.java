package com.example.book_author.service.impl;

import com.example.book_author.dto.AuthorDto;
import com.example.book_author.dto.AuthorForm;
import com.example.book_author.entity.Author;
import com.example.book_author.entity.SuccessDto;
import com.example.book_author.exception.AppException;
import com.example.book_author.repository.AuthorRepository;
import com.example.book_author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto add(AuthorForm form) {
        if (authorRepository.existsByAuthorCodeAndDeletedFalse(form.getAuthorCode())) {
            throw new AppException("Author already exists by authorCode: " + form.getAuthorCode());
        }
        Author author = new Author();
        author.setFullName(form.getFullName());
        author.setCountry(form.getCountry());
        Author saveAuthor = authorRepository.save(author);
        AuthorDto dto = AuthorDto.toDto(saveAuthor);
        return dto;
    }

    @Override
    public AuthorDto update(Long id, AuthorForm form) {
        Author author = authorRepository.findByIdAndDeletedFalse(id);
        if (author == null) {
            throw new AppException("Author not found by id: " + id);
        }
        author.setCountry(form.getCountry());
        author.setFullName(form.getFullName());
        Author updateAuthor = authorRepository.save(author);
        AuthorDto dto = AuthorDto.toDto(updateAuthor);
        return dto;
    }

    @Override
    public AuthorDto getOne(Long id) {
        Author author = authorRepository.findByIdAndDeletedFalse(id);
        if (author == null) {
            throw new AppException("Author not found by id: " + id);
        }
        AuthorDto dto = AuthorDto.toDto(author);
        return dto;
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAllByDeletedFalse();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : authors) {
            AuthorDto dto = AuthorDto.toDto(author);
            authorDtos.add(dto);
        }
        return authorDtos;
    }

    @Override
    public SuccessDto delete(Long id) {
        Author author = authorRepository.findByIdAndDeletedFalse(id);
        if (author == null) {
            throw new AppException("Author not found by id: " + id);
        }
        author.setDeleted(true);
        authorRepository.save(author);
        SuccessDto dto = new SuccessDto();
        dto.setMessage("Author successfully deleted!");
        return dto;
    }

    @Override
    public AuthorDto getDeletedOne(Long id) {
        Author author = authorRepository.findByIdAndDeletedTrue(id);
        if (author==null){
            throw new AppException("Author not found by id: " + id);
       /* } else if (author.getDeleted().equals(true)){*/
        }
       /* throw new AppException("there is no such author");*/
        return AuthorDto.toDto(author);
    }

    @Override
    public List<AuthorDto> getAllDeleted() {
        List<Author> authorList = authorRepository.findAllByDeletedTrue();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author : authorList) {
            AuthorDto dto = AuthorDto.toDto(author);
            authorDtoList.add(dto);
        }
        return authorDtoList;
    }
}
