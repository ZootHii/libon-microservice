package com.libon.libraryservice.client;

import com.libon.libraryservice.dto.BookDto;
import com.libon.libraryservice.dto.BookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", path = "/v1/books")
public interface BookServiceClient {

    @GetMapping
    ResponseEntity<List<BookDto>> getAll();

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookIdDto> getByIsbn(@PathVariable String isbn);

    @GetMapping("/id/{id}")
    ResponseEntity<BookDto> getById(@PathVariable String id);
}
