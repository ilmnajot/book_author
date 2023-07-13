package com.example.book_author.dto.mapper;

import com.example.book_author.dto.AuthorDto;
import com.example.book_author.dto.BookDto;
import com.example.book_author.entity.Author;
import com.example.book_author.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);


    AuthorDto authorToAuthorDto(Author author);


    Author authorDtoToAuthor(AuthorDto authorDto);


}
