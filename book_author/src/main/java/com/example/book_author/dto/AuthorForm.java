package com.example.book_author.dto;

import lombok.Data;

@Data
public class AuthorForm {
    private String fullName;
    private String country;
    private Long authorCode;
}
