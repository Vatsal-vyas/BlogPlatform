package com.vatsal.blogPlatform.repository;

import com.vatsal.blogPlatform.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByUsername(String username);
    Author findByEmail(String email);
}
