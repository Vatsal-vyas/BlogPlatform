package com.vatsal.blogPlatform.service;

import com.vatsal.blogPlatform.exception.ResourceNotFoundException;
import com.vatsal.blogPlatform.model.Author;
import com.vatsal.blogPlatform.model.Blog;
import com.vatsal.blogPlatform.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthorService authorService;

    public BlogService(BlogRepository blogRepository, AuthorService authorService) {
        this.blogRepository = blogRepository;
        this.authorService = authorService;
    }

    @Transactional
    public Blog createBlog(String title, String content, Long authorId) {
        Author author = authorService.getAuthorById(authorId);
        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setContent(content);
        newBlog.setAuthor(author);

        return blogRepository.save(newBlog);
    }

    @Transactional(readOnly = true)
    public Blog getBlogById(Long id){
        return blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID :" + id));
    }

    @Transactional(readOnly = true)
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    @Transactional
    public Blog updateBlog(Long id, String newTitle, String newContent){
        Blog existingBlog = getBlogById(id);
        if(newContent != null && !newContent.isBlank()) {
            existingBlog.setContent(newContent);
        }
        if(newTitle != null && !newTitle.isBlank()) {
            existingBlog.setTitle(newTitle);
        }
        return blogRepository.save(existingBlog);
    }

    @Transactional
    public void deleteBlog(Long id){
        getBlogById(id);
        blogRepository.deleteById(id);
    }
}
