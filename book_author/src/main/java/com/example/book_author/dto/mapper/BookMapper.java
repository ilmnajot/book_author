package com.example.book_author.dto.mapper;

import com.example.book_author.dto.BookDto;
import com.example.book_author.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);


    BookDto bookToBookDto(Book book);


    Book bookDtoToBook(BookDto bookDto);
}
