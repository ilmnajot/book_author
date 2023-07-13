package com.example.book_author.controller;

import com.example.book_author.dto.BookDto;
import com.example.book_author.dto.BookForm;
import com.example.book_author.entity.SuccessDto;
import com.example.book_author.repository.AuthorRepository;
import com.example.book_author.service.BookService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }
    @PostMapping("/add")
    public HttpEntity<BookDto> addBook(@RequestBody BookForm form){
        BookDto bookDto = bookService.add(form);
        return ResponseEntity.ok(bookDto);
    }
    @GetMapping("/{id}")
    public HttpEntity<BookDto> getOneBook(@PathVariable("id") Long id){
        BookDto bookDto = bookService.getOne(id);
        return ResponseEntity.ok(bookDto);
    }
    @GetMapping("/all")
    public HttpEntity<List<BookDto>> getAllBook(){
        List<BookDto> dtoList = bookService.getAll();
        return ResponseEntity.ok(dtoList);
    }
    @PutMapping("/{id}")
    public HttpEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookForm form){
        BookDto updated = bookService.update(id, form);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<SuccessDto> deleteBook(@PathVariable Long id) {
        SuccessDto successDto = bookService.delete(id);
        return ResponseEntity.ok(successDto);
    }
}
