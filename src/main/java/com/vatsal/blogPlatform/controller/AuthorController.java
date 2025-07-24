package com.vatsal.blogPlatform.controller;

import com.vatsal.blogPlatform.dto.AuthorRegistrationRequest;
import com.vatsal.blogPlatform.dto.AuthorResponse;
import com.vatsal.blogPlatform.dto.AuthorUpdateRequest;
import com.vatsal.blogPlatform.model.Author;
import com.vatsal.blogPlatform.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> registerAuthor(@Valid @RequestBody AuthorRegistrationRequest request) {
        Author registeredAuthor = authorService.registerUser(
                request.getName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        AuthorResponse response = new AuthorResponse(registeredAuthor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        AuthorResponse response = new AuthorResponse(author);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorUpdateRequest request){
        Author updatedAuthor = authorService.updateAuthor(id, request.getName(),request.getEmail());
        AuthorResponse response = new AuthorResponse(updatedAuthor);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
