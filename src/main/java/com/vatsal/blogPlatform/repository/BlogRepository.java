package com.vatsal.blogPlatform.repository;

import com.vatsal.blogPlatform.model.Author;
import com.vatsal.blogPlatform.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitleContainingIgnoreCase(String titleKeyword);

    List<Blog> findByAuthor(Author author);

    List<Blog> findByAuthorOrderByCreatedAtDesc(Author author);
}
