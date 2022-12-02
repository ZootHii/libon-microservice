package com.libon.bookservice.controller;

import com.libon.bookservice.dto.BookDto;
import com.libon.bookservice.dto.BookIdDto;
import com.libon.bookservice.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
@Validated
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getByIsbn(@PathVariable @NotEmpty String isbn) {
        return ResponseEntity.ok(service.findByIsbn(isbn));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(service.findDetailsById(id));
    }
}
