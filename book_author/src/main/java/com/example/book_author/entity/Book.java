package com.example.book_author.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String language;

    private Long isbn;

    private Boolean deleted = false;

    @ManyToMany
    private List<Author> authors;
}
