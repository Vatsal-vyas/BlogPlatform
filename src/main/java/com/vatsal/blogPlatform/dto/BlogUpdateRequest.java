package com.vatsal.blogPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUpdateRequest {

    @Size(min = 3, max = 255, message = "title should be more than 3 characters and less than 255 characters")
    private String title;

    @Size(min = 10, message = "content should be minimum 10 characters long")
    private String content;
}
