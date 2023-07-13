package com.example.book_author.dto;

import com.example.book_author.entity.Book;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String description;
    private String language;

    public static BookDto toDto(Book saveBook) {
        BookDto dto = new BookDto();
        dto.setId(saveBook.getId());
        dto.setName(saveBook.getName());
        dto.setDescription(saveBook.getDescription());
        dto.setLanguage(saveBook.getLanguage());
        return dto;
    }
}

