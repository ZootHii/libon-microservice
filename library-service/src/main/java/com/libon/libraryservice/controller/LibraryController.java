package com.libon.libraryservice.controller;

import com.libon.libraryservice.dto.AddBookRequest;
import com.libon.libraryservice.dto.LibraryDto;
import com.libon.libraryservice.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/v1/libraries")
@Validated
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getAllBooksInLibraryById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(libraryService.findAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> create() {
        return ResponseEntity.ok(libraryService.create());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest addBookRequest) {
        libraryService.addBookToLibrary(addBookRequest);
        return ResponseEntity.ok().build();
    }
}
