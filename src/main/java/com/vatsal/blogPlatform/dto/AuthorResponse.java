package com.vatsal.blogPlatform.dto;

import com.vatsal.blogPlatform.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private String username;
    private String email;

    public AuthorResponse(Author author){
        this.id = author.getId();
        this.name = author.getName();
        this.username = author.getUsername();
        this.email = author.getEmail();
    }
}
