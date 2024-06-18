package com.springboot.blog.controller;

import com.springboot.blog.payload.ApiResponse;
import com.springboot.blog.payload.PostDto;
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
        ApiResponse<PostDto> response = new ApiResponse<>("Post created successfully", createdPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        ApiResponse<List<PostDto>> response = new ApiResponse<>("Posts retrieved successfully", posts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable(name = "id") long id) {
        PostDto postDto = postService.getPostById(id);
        ApiResponse<PostDto> response = new ApiResponse<>("Post found", postDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // update post by id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto updatedPost =  postService.updatePost(postDto, id);
        ApiResponse<PostDto> response = new ApiResponse<>("Post updated", updatedPost);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        ApiResponse<Void> response = new ApiResponse<>("Post deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
