package com.example.book_author.service;

import com.example.book_author.dto.BookDto;
import com.example.book_author.dto.BookForm;
import com.example.book_author.entity.SuccessDto;

import java.util.List;

public interface BookService {
    BookDto add(BookForm form);

    BookDto getOne(Long id);

    List<BookDto> getAll();

    BookDto update(Long id, BookForm form);

    SuccessDto delete(Long id);

}
