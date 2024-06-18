package com.springboot.blog.controller;

import com.springboot.blog.payload.ApiResponse;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create blog post rest API
    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        ApiResponse<PostDto> response = new ApiResponse<>("Post created successfully", createdPost, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PostResponse>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PostResponse posts = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        ApiResponse<PostResponse> response = new ApiResponse<>("Posts retrieved successfully", posts, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable(name = "id") long id) {
        PostDto postDto = postService.getPostById(id);
        ApiResponse<PostDto> response = new ApiResponse<>("Post found", postDto, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // update post by id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto updatedPost =  postService.updatePost(postDto, id);
        ApiResponse<PostDto> response = new ApiResponse<>("Post updated", updatedPost, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        ApiResponse<Void> response = new ApiResponse<>("Post deleted successfully", null, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
