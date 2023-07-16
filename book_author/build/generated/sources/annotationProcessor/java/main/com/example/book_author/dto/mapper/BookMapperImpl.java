package com.example.book_author.dto.mapper;

import com.example.book_author.dto.BookDto;
import com.example.book_author.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T06:39:24+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setLanguage( book.getLanguage() );

        return bookDto;
    }

    @Override
    public Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDto.getId() );
        book.setName( bookDto.getName() );
        book.setDescription( bookDto.getDescription() );
        book.setLanguage( bookDto.getLanguage() );

        return book;
    }
}
