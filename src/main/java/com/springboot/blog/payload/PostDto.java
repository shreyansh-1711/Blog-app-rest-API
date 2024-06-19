package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 char")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Description should have atleast 10 char")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
