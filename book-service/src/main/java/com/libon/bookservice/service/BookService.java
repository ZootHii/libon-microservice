package com.libon.bookservice.service;

import com.libon.bookservice.dto.BookDto;
import com.libon.bookservice.dto.BookIdDto;
import com.libon.bookservice.exception.BookNotFoundException;
import com.libon.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookDto> findAll() {
        return repository.findAll()
                .stream()
                .map(BookDto::convert)
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return repository.findByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + isbn));
    }

    public BookDto findDetailsById(String id) {
        return repository.findById(id)
                .map(BookDto::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: " + id));
    }
}
