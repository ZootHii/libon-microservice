package com.libon.libraryservice.service;

import com.libon.libraryservice.client.BookServiceClient;
import com.libon.libraryservice.dto.AddBookRequest;
import com.libon.libraryservice.exception.LibraryNotFoundException;
import com.libon.libraryservice.model.Library;
import com.libon.libraryservice.dto.LibraryDto;
import com.libon.libraryservice.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository repository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository repository, BookServiceClient bookServiceClient) {
        this.repository = repository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto findAllBooksInLibraryById(String id) {
        Library library = repository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id:" + id));

        return new LibraryDto(
                library.getId(),
                library.getUserBook().stream()
                        .map(bookServiceClient::getById) // feign client
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList()));
    }

    public LibraryDto create() {
        Library newLibrary = repository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest addBookRequest) {
        String bookId = bookServiceClient.getByIsbn(addBookRequest.getIsbn()).getBody().getId();
        Library library = repository.findById(addBookRequest.getLibraryId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id:" + addBookRequest.getLibraryId()));
        library.getUserBook().add(bookId);
        repository.save(library);
    }
}
