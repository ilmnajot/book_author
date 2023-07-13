package com.example.book_author.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookForm {

    private String name;

    private String description;

    private String language;

    private Long isbn;

    private List<Long> authorIds;
}
