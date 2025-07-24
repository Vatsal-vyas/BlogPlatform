package com.vatsal.blogPlatform.dto;

import com.vatsal.blogPlatform.model.Author;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreationRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 255, message = "title should be more than 3 characters and less than 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, message = "Content must be more than 10 characters")
    private String content;

    @NotNull(message = "Author ID is required")
    @Min(value = 1, message = "Author ID must be a positive number")
    private Long authorId;
}
