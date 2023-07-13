package com.example.book_author.repository;

import com.example.book_author.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbnAndDeletedFalse(Long isbn);

        Book findByIdAndDeletedFalse(Long id);

        List<Book> findAllByDeletedFalse();



}
