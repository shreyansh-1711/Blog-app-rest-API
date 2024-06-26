package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @NotEmpty()
    @Size(min = 10, message = "Should have at least 10 char")
    private String body;
}
