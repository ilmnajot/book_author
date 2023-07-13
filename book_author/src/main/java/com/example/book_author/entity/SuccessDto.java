package com.example.book_author.entity;

import lombok.Data;

@Data
public class SuccessDto {

    private Boolean success = true;

    private String message;
}
