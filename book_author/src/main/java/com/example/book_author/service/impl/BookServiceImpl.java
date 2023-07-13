package com.example.book_author.service.impl;

import com.example.book_author.dto.BookDto;
import com.example.book_author.dto.BookForm;
import com.example.book_author.dto.mapper.BookMapper;
import com.example.book_author.entity.Author;
import com.example.book_author.entity.Book;
import com.example.book_author.entity.SuccessDto;
import com.example.book_author.exception.AppException;
import com.example.book_author.repository.AuthorRepository;
import com.example.book_author.repository.BookRepository;
import com.example.book_author.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
//    private final BookMapper bookMapper;


    @Override
    public BookDto add(BookForm form) {
        if (bookRepository.existsByIsbnAndDeletedFalse(form.getIsbn())) {
            throw new AppException("Book already exists by isbn: " + form.getIsbn());
        }
        Book book = new Book();
        book.setName(form.getName());
        book.setDescription(form.getDescription());
        book.setLanguage(form.getLanguage());
        List<Author> authorList = authorRepository.findAllByIdInAndDeletedFalse(form.getAuthorIds());
        book.setAuthors(authorList);
        Book saveBook = bookRepository.save(book);
//        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(saveBook);
        BookDto dto = BookDto.toDto(saveBook);
        return dto;
    }

    @Override
    public BookDto update(Long id, BookForm form) {
        Book book = bookRepository.findByIdAndDeletedFalse(id);
        if (book==null){
            throw new AppException("Book not found with id " + id);
        }
        book.setName(form.getName());
        book.setDescription(form.getDescription());
        book.setLanguage(form.getLanguage());
        book.setIsbn(form.getIsbn());
        book.setId(id);
        Book saved = bookRepository.save(book);
        return BookDto.toDto(saved);
    }

    @Override
    public BookDto getOne(Long id) {
        Book book= bookRepository.findByIdAndDeletedFalse(id);
        if (book!=null){
        return BookDto.toDto(book);
        }
        throw  new AppException("there is no book with id " + id);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = bookRepository.findAllByDeletedFalse();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : bookList) {
            BookDto dto = BookDto.toDto(book);
            bookDtos.add(dto);
        }
        return bookDtos;
    }
    @Override
    public SuccessDto delete(Long id) {
        Book book = bookRepository.findByIdAndDeletedFalse(id);
        if (book==null){
            throw new AppException("Book not found with id " + id);
        }
        book.setDeleted(true);
        bookRepository.save(book);
        SuccessDto successDto = new SuccessDto();
        successDto.setMessage("Book deleted successfully");
        return successDto;
    }
}
