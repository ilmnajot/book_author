package com.example.book_author.controller;

import com.example.book_author.dto.AuthorDto;
import com.example.book_author.dto.AuthorForm;
import com.example.book_author.entity.SuccessDto;
import com.example.book_author.service.AuthorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("/add")
    public HttpEntity<AuthorDto> saveAuthor(@RequestBody AuthorForm form){
        AuthorDto addedAuthor = authorService.add(form);
    return ResponseEntity.ok(addedAuthor);
    }
    @GetMapping("/{id}")
    public HttpEntity<AuthorDto> getOneAuthor(@PathVariable("id") Long id){
        AuthorDto authorDto = authorService.getOne(id);
        return ResponseEntity.ok(authorDto);
    }
    @GetMapping("/all")
    public HttpEntity<List<AuthorDto>> getAllAuthor(){
        List<AuthorDto> all = authorService.getAll();
        return ResponseEntity.ok(all);
    }
    @PutMapping("/{id}")
    public HttpEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorForm form){
        AuthorDto updated = authorService.update(id, form);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<SuccessDto> deleteAuthor(@PathVariable Long id){
        SuccessDto successDto = authorService.delete(id);
        return ResponseEntity.ok(successDto);

    }
    @GetMapping("/admin/{id}")
    public HttpEntity<AuthorDto> getDeletedAuthor(@PathVariable("id") Long id){
        AuthorDto deletedOne = authorService.getDeletedOne(id);
        return ResponseEntity.ok(deletedOne);
    }
    @GetMapping("/allDeleted")
    public HttpEntity<List<AuthorDto>> getAllDeletedOnes(){
        List<AuthorDto> allDeleted = authorService.getAllDeleted();
        return ResponseEntity.ok(allDeleted);
    }
}
