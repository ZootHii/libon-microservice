package com.libon.bookservice.repository;

import com.libon.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
}
