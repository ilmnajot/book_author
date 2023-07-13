package com.example.book_author.dto;

import com.example.book_author.entity.Author;
import lombok.Data;

@Data
public class AuthorDto {

    private Long id;
    private String fullName;

    public static AuthorDto toDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFullName(author.getFullName());
        return dto;
    }
}
