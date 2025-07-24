package com.vatsal.blogPlatform.controller;

import com.vatsal.blogPlatform.dto.BlogCreationRequest;
import com.vatsal.blogPlatform.dto.BlogResponse;
import com.vatsal.blogPlatform.dto.BlogUpdateRequest;
import com.vatsal.blogPlatform.model.Blog;
import com.vatsal.blogPlatform.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@Valid @RequestBody BlogCreationRequest request){
        Blog createdBlog =blogService.createBlog(
                request.getTitle(),
                request.getContent(),
                request.getAuthorId()
        );
        BlogResponse response = new BlogResponse(createdBlog);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id){
        Blog blog = blogService.getBlogById(id);
        BlogResponse response = new BlogResponse(blog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BlogResponse>> getAllBlogs(){
        List<Blog> blogs = blogService.getAllBlogs();
        List<BlogResponse> responses = blogs.stream().map(BlogResponse::new).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogUpdateRequest request){
        Blog updatedBlog = blogService.updateBlog(
                id,
                request.getTitle(),
                request.getContent()
                );
        BlogResponse response = new BlogResponse(updatedBlog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
    }
}
