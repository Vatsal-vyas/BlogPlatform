package com.vatsal.blogPlatform.service;

import com.vatsal.blogPlatform.exception.DuplicateResourceException;
import com.vatsal.blogPlatform.exception.ResourceNotFoundException;
import com.vatsal.blogPlatform.model.Author;
import com.vatsal.blogPlatform.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author registerUser(String name, String username, String email, String password) {
        if (authorRepository.findByEmail(email) != null) {
            throw new DuplicateResourceException("User with this email already exists");
        }
        if (authorRepository.findByUsername(username) != null) {
            throw new DuplicateResourceException(("User with this username already exists"));
        }
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setEmail(email);
        newAuthor.setUsername(username);
        newAuthor.setPassword(password);

        return authorRepository.save(newAuthor);
    }

    @Transactional(readOnly = true)
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with ID :" + id));
    }

    @Transactional
    public Author updateAuthor(Long id, String newName, String newEmail) {
        Author existingAuthor = getAuthorById(id);
        if (newName != null && !newName.isBlank()) {
            existingAuthor.setName(newName);
        }
        if (newEmail != null && !newEmail.isBlank()) {
            Author authorWithSameEmail = authorRepository.findByEmail(newEmail);
            if (authorWithSameEmail != null && !authorWithSameEmail.getId().equals(id)) {
                throw new DuplicateResourceException("Email '" + newEmail + "' is already taken by another user");
            }
            existingAuthor.setEmail(newEmail);
        }
        return authorRepository.save(existingAuthor);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        getAuthorById(id);
        authorRepository.deleteById(id);
    }
}
