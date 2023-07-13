package com.example.book_author.repository;

import com.example.book_author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByIdAndDeletedFalse(Long id);

    List<Author> findAllByDeletedFalse();

    boolean existsByAuthorCodeAndDeletedFalse(Long authorCode);

    List<Author> findAllByIdInAndDeletedFalse(List<Long> authorIds);

    Author findByIdAndDeletedTrue(Long id);
    List<Author> findAllByDeletedTrue();
}
